import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {IndexQuestionService} from "../service/index-question.service";
import {BehaviorSubject, EMPTY, Observable, Subscription} from "rxjs";
import {getDoc, getDocs, query, where} from "@angular/fire/firestore";
import {collection, doc, setDoc} from "firebase/firestore";
import {db} from "../../environments/test";
import {Miahoot} from "../service/interfaces";
import {User} from "@angular/fire/auth";

interface answer {
  idQuestion: number;
  labelQuestion: string;
  idReponse: number;
  labelReponse: string;
  nbVotes: number;
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
      console.log('x')
    });



    //for each question and foreach reponse of these questions, ask firebase the number of participants who answered this question with this answer

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
                this.reponses.push({idQuestion: question.id!, labelQuestion: question.label, idReponse: reponse.id!, labelReponse:reponse.label ,  nbVotes: reponseData.length});
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
