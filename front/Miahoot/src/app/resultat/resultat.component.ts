import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IndexQuestionService} from "../service/index-question.service";
import {BehaviorSubject, EMPTY, Observable, Subscription, timeout} from "rxjs";
import {getDoc, getDocs, query, where} from "@angular/fire/firestore";
import {collection, doc, setDoc} from "firebase/firestore";
import {db} from "../../environments/test";
import {Miahoot, Reponse} from "../service/interfaces";
import {User} from "@angular/fire/auth";
import { Chart} from "chart.js/auto";

interface answer {
  index: number;
  idQuestion: number;
  labelQuestion: string;
  idReponse: number;
  labelReponse: string;
  nbVotes: number;
}
interface saveAnswer {

  idQuestion: number;
  labelQuestion: string;
  listeReponse: answer[];
}


@Component({
  selector: 'app-resultat',
  templateUrl: './resultat.component.html',
  styleUrls: ['./resultat.component.scss']
})
export class ResultatComponent implements OnInit {

  public routeSub: Subscription | undefined = undefined;
  codePartie ='';
  miahootPartie!: Miahoot;
  savereponses : any;

  selectedResultat: any = null;

  reponses: answer[] = [];
  public readonly liste_reponses: BehaviorSubject<answer[] | null>;


  constructor (private route: ActivatedRoute, private cdr: ChangeDetectorRef, private iq: IndexQuestionService) {
    this.liste_reponses = new BehaviorSubject<answer[] | null>(null,);

  }

  async ngOnInit(): Promise<void> {
    this.routeSub = this.route.params.subscribe(params => {
      this.codePartie = params['codePartie'];
      console.log(this.codePartie)
    });
    const q = query(collection(db, `parties`), where("codePartie", "==", `${this.codePartie}`));
    getDocs(q).then((querySnapshot) => {
      querySnapshot.forEach((doc) => {
        this.miahootPartie = doc.get('miahoot');
        console.log(this.miahootPartie)
      });

    }).then(() => {

      this.getAnswer();
      console.log('liste reponse ', this.liste_reponses)



      console.log('x')
    });
    //this.createCanva();


    //for each question and foreach reponse of these questions, ask firebase the number of participants who answered this question with this answer

  }

  onButtonClick(index: number){
    this.selectedResultat = index;
    console.log('index ', index)
    document.getElementById('acquisitions'+index)!.attributes.removeNamedItem('hidden');
    this.createCanva(index);
  }
  createCanva(index:number) {
    console.log('index ' , index)
    let x = this.reponses;
    let tabrep:string[] = [];
    let tabvote:number[] = [];
     x.forEach((question) => {
      if(question.index == index){
        tabrep.push(question.labelReponse);
        tabvote.push(question.nbVotes);
      }
    });
    let valise = 'acquisitions';
    let w = tabrep[0];
    const data = [
      { label: tabrep[0], count: tabvote[0] },
      { label: tabrep[1], count: tabvote[1]},
      { label: tabrep[2], count: tabvote[2]},
      { label: tabrep[3], count: tabvote[3]},

    ];
    data

      valise = 'acquisitions' + index;
      let myElem = document.getElementById(valise);
      console.log('ici ', valise);
      console.log('myelem ' , myElem)
      if (myElem) {
        new Chart(myElem as HTMLCanvasElement, {
          type: 'bar',
          data: {
            labels: data.map(row => row.label),
            datasets: [
              {
                label: 'Nombre de votes',
                data: data.map(row => row.count)
              }
            ]
          }
        });
      }
   // });
  }


    getAnswer(){
    this.miahootPartie.questions.forEach((question, index) => {
      this.miahootPartie.questions[index].reponses.forEach((reponse) => {
        const partieRef = doc(db, "parties", this.codePartie);
        const questionRef = doc(partieRef, 'questions', question.id!.toString());
        const reponseRef = doc(questionRef, 'reponses', reponse.id!.toString());

        getDoc(reponseRef)
          .then((docSnapshot) => {
            if (docSnapshot.exists()) {
              const reponseData:string[] = docSnapshot.get('nbVotes');
              if (reponseData != undefined) {
                this.reponses.push({index: index, idQuestion: question.id!, labelQuestion: question.label, idReponse: reponse.id!, labelReponse:reponse.label ,  nbVotes: reponseData.length});
                this.savereponses = this.reponses;
                const nombreParticipantsVote= reponseData.length;
                console.log(`La réponse ${reponse.id} de la question ${question.id} a été votée par ${nombreParticipantsVote} participants.`);
              }else {
                console.log(`La réponse ${reponse.id}  de la question ${question.id} n'a pas été votée par de participants.`);
              }
              // Mettre à jour le nombre de participants dans l'objet de réponse
              //reponse.nombreParticipantsVote = nombreParticipantsVote;

              // Tu peux également mettre à jour l'objet de réponse dans `this.miahoot.questions` si nécessaire
            }

            console.log(this.reponses)

          }).then(() => {

          this.liste_reponses.next(this.reponses);

        })
          .catch((error) => {
            console.error(`Erreur lors de la récupération de la réponse ${reponse.id} :`, error);
          });
      });



    });

  }



}
