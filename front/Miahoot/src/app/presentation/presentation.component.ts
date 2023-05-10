import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {DsService, Enseignant, Miahoot, Question} from "../service/ds.service";
import {UserService} from "../service/user.service";
import {BehaviorSubject, firstValueFrom, Subscription} from "rxjs";
import {user} from "@angular/fire/auth";
import {Reponse} from "../service/interfaces";
import {ActivatedRoute} from "@angular/router";
import {getDocs, query, where} from "@angular/fire/firestore";
import {collection, doc} from "firebase/firestore";
import {db} from "../../environments/test";

@Component({
  selector: 'app-presentation',
  templateUrl: './presentation.component.html',
  styleUrls: ['./presentation.component.scss']
})
export class PresentationComponent implements OnInit {
  commencePartie : boolean = false
  estPresentateur : boolean = false
  indexQuestionCourante = 0;
  reponsesUtilisateur: number[] = [];
  public readonly question_courante: BehaviorSubject<Question | null>;
  /*miahootCourant : Miahoot = {
    "id": 1,
    "nom": "Quizz Web",
    "questions": [
      {
        "id": 1,
        "label": "Qu'est-ce qu'un \"URL\" ?",
        "reponses": [
          {
            "id": 1,
            "label": "Un identifiant unique pour une page web",
            "estValide": true
          },
          {
            "id": 2,
            "label": "Un souley",
            "estValide": false
          },
          {
            "id": 3,
            "label": "Iliany",
            "estValide": false
          },
          {
            "id": 4,
            "label": "quoicoubeh",
            "estValide": false
          }
        ]
      },
      {
        "id": 2,
        "label": "Qu'est-ce qu'un Bg ?",
        "reponses": [{
          "id": 1,
          "label": "BG TRUE",
          "estValide": true
        },
          {
            "id": 2,
            "label": "BG FALSE",
            "estValide": false
          },]
      },
      {
        "id": 3,
        "label": "1+1 ? ?",
        "reponses": [
          {
            "id": 1,
            "label": "2",
            "estValide": true
          },
          {
            "id": 2,
            "label": "11",
            "estValide": false
          },
        ]
      }
    ]
  };*/
  showCorrectAnswer = false;
  public routeSub: Subscription | undefined = undefined;
  codePartie ='';
  miahootPartie !: Miahoot ;
  constructor(private auth :  AuthService, private ds :DsService, private us:UserService, private route: ActivatedRoute) {
    this.question_courante = new BehaviorSubject<Question | null>(null,);

  }

  async ngOnInit(){
    this.routeSub = this.route.params.subscribe(params => {
      console.log(params) //log the entire params object
      console.log(params['codePartie']) //log the value of id
      this.codePartie = params['codePartie'];
    });
    console.log("ici")
    const q = query(collection(db, `parties`), where("codePartie", "==", this.codePartie));
    const querySnapshot = await getDocs(q);
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.get('miahoot'), " => ", doc.data());
      this.miahootPartie = doc.get('miahoot');
    });


    await this.isEnseignant();
    this.question_courante.next(this.miahootPartie.questions[this.indexQuestionCourante]);

  }

  async isEnseignant(){
    await firstValueFrom(this.auth.currentUser).then(
      (u) => {
        if(u!.isAnonymous){
          this.estPresentateur = false
        }else {
          this.estPresentateur = true
        }
      }
    )
  }

  partieEnCours(){
    this.commencePartie = true
  }

  questionSuivante() {
    this.indexQuestionCourante++;
    if (this.indexQuestionCourante < this.miahootPartie.questions.length) {
      this.question_courante.next(this.miahootPartie.questions[this.indexQuestionCourante]);
    }
  }

  afficherReponse(reponse: string) {
    console.log("Réponse sélectionnée : ", reponse);
  }

  toggleReponse(reponse: Reponse) {
    if(reponse.id == undefined){
      reponse.id = 0;
    }
    const reponseId = reponse.id;
    const index = this.reponsesUtilisateur.indexOf(reponseId);
    if (index === -1) {
      this.reponsesUtilisateur.push(reponseId);
    } else {
      this.reponsesUtilisateur.splice(index, 1);
    }
  }

  confirmerChoix() {
    const resp = this.reponsesUtilisateur.indexOf
    console.log(this.reponsesUtilisateur);
  }



}
