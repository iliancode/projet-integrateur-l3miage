import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {DsService, Enseignant, Miahoot, Question} from "../service/ds.service";
import {UserService} from "../service/user.service";
import {BehaviorSubject, firstValueFrom, Observable, Subscription} from "rxjs";
import {user} from "@angular/fire/auth";
import {Reponse} from "../service/interfaces";
import {ActivatedRoute} from "@angular/router";
import {addDoc, getDocs, onSnapshot, query, updateDoc, where} from "@angular/fire/firestore";
import {collection, doc, getFirestore, setDoc} from "firebase/firestore";
import {db} from "../../environments/test";
import {getDatabase, set} from "@angular/fire/database";
import {ref} from "@angular/fire/storage";
import {AngularFireList} from "@angular/fire/compat/database";
import {IndexQuestionService} from "../service/index-question.service";
//import {AngularFirestore} from "@angular/fire/compat/firestore";

@Component({
  selector: 'app-presentation',
  templateUrl: './presentation.component.html',
  styleUrls: ['./presentation.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentationComponent implements OnInit {

  commencePartie : boolean = false
  estPresentateur : boolean = false
  indexQuestionCourante = 0;
  reponsesUtilisateur: number[] = [];
  public readonly question_courante: BehaviorSubject<Question | null>;

  showCorrectAnswer = false;
  public routeSub: Subscription | undefined = undefined;
  codePartie ='';
  miahootPartie !: Miahoot ;
  indexRecup = 0;
  public currentIndex:any ;
  //dbb:any;
  constructor(private auth :  AuthService, private ds :DsService, private us:UserService, private route: ActivatedRoute,
              private indexQuestionService:IndexQuestionService , private cdr: ChangeDetectorRef){
    this.question_courante = new BehaviorSubject<Question | null>(null,);
    this.indexQuestionService.currentQuestion$.subscribe((index) => {
      this.currentIndex = index;
      console.log("index courant: ", this.currentIndex)
      this.cdr.markForCheck();
    });
    //this.dbb = firestore;


  }

  async ngOnInit(){
    this.routeSub = this.route.params.subscribe(params => {
      this.codePartie = params['codePartie'];
    });
    const q = query(collection(db, `parties`), where("codePartie", "==", this.codePartie));
    const querySnapshot = await getDocs(q);
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.get('miahoot'), " => ", doc.data());
      this.miahootPartie = doc.get('miahoot');
    });

    await this.isEnseignant();

    this.question_courante.next(this.miahootPartie.questions[this.indexQuestionCourante]);

    const unsub = onSnapshot(doc(db, "parties", this.codePartie), (doc) => {
      console.log("Miahoot modifié: ", doc.get('indexQuestionCourante'));
      this.indexRecup = doc.get('indexQuestionCourante');
      this.indexQuestionService.setCurrentQuestion(this.indexRecup);
    });
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

  async questionSuivante() {

    this.indexQuestionCourante++;

    console.log('question courante : ' + this.question_courante.getValue()?.label);

    updateDoc(doc(db, "parties", this.codePartie), {
      indexQuestionCourante: this.indexQuestionCourante
    }).then(() => {
      console.log("Document successfully updated!");
      if (this.indexQuestionCourante < this.miahootPartie.questions.length) {
        this.question_courante.next(this.miahootPartie.questions[this.indexRecup]);
      }
    });


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

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
      const collParticipant = collection(this.us.getFirestore(), `parties/${this.codePartie}/${this.miahootPartie.questions[this.indexQuestionCourante].id }/participants/${this.reponsesUtilisateur}` );
      addDoc(collParticipant, {
        uid: user?.uid??'',

      });


    }) ;


  }



}
