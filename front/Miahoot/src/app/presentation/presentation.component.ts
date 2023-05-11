import {ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {DsService, Enseignant, Miahoot, Question} from "../service/ds.service";
import {UserService} from "../service/user.service";
import {BehaviorSubject, firstValueFrom, Observable, Subscription} from "rxjs";
import {user} from "@angular/fire/auth";
import {Reponse} from "../service/interfaces";
import {ActivatedRoute} from "@angular/router";
import {addDoc, arrayUnion, getDocs, onSnapshot, query, updateDoc, where} from "@angular/fire/firestore";
import {collection, doc, getFirestore, setDoc} from "firebase/firestore";
import {db} from "../../environments/test";
import {getDatabase, increment, set} from "@angular/fire/database";
import {ref} from "@angular/fire/storage";
import {AngularFireList} from "@angular/fire/compat/database";
import {IndexQuestionService} from "../service/index-question.service";
//import {AngularFirestore} from "@angular/fire/compat/firestore";
import 'firebase/firestore';
@Component({
  selector: 'app-presentation',
  templateUrl: './presentation.component.html',
  styleUrls: ['./presentation.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentationComponent implements OnInit {

  commencePartie : boolean = false
  estPresentateur : boolean = false
  indexQuestionCourante = -1;
  reponsesUtilisateur: number[] = [];
  public readonly question_courante: BehaviorSubject<Question | null>;
  selectedReponse: any = null;

  uid = '';
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

  }

  async ngOnInit(){
    this.routeSub = this.route.params.subscribe(params => {
      this.codePartie = params['codePartie'];
    });
    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
      this.uid = user?.uid!;
    }) ;
    const q = query(collection(db, `parties`), where("codePartie", "==", this.codePartie));
    const querySnapshot = await getDocs(q);
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.get('miahoot'), " => ", doc.data());
      this.miahootPartie = doc.get('miahoot');
    });

    await this.isEnseignant();
    if(this.indexQuestionCourante >= 0){
      this.question_courante.next(this.miahootPartie.questions[this.indexQuestionCourante]);
    }

    const unsub = onSnapshot(doc(db, "parties", this.codePartie), (doc) => {
      console.log("Miahoot modifié: ", doc.get('indexQuestionCourante'));
      this.indexRecup = doc.get('indexQuestionCourante');
      console.log("index recuperer = " + this.indexRecup)
      if(this.indexRecup >=0){
        this.indexQuestionService.setCurrentQuestion(this.indexRecup);
      }
      if(this.indexRecup  != -1){
        this.commencePartie = true
      }
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
        this.cdr.markForCheck();

      }
    )
  }

  partieEnCours(){


  }

  async questionSuivante() {

    //console.log("this.miahootPartie.questions.length " + this.miahootPartie.questions.length);
    console.log("indexQuestionCourante " + this.indexQuestionCourante)
    this.indexQuestionCourante++;
    console.log("indexQuestionCourante++ " + this.indexQuestionCourante)
    // console.log('question courante : ' + this.question_courante.getValue()?.label);

    if(this.indexQuestionCourante >= 0){
      if(this.miahootPartie.questions.length <= this.indexQuestionCourante){
        window.location.href = '/resultats/' + this.codePartie;
        console.log("ya plus de question bozo");

      }else {


        updateDoc(doc(db, "parties", this.codePartie), {
          indexQuestionCourante: this.indexQuestionCourante
        }).then(() => {
          console.log("Document successfully updated!");
          if (this.indexQuestionCourante < this.miahootPartie.questions.length) {
            this.question_courante.next(this.miahootPartie.questions[this.indexRecup]);
          }
        });
      }
    }
  }

  onButtonClick(reponse: Reponse){
    this.selectedReponse = reponse;
    document.getElementById('buttonConfirm')!.attributes.removeNamedItem('hidden');

  }

  confirmerChoix() {
    let reponse= document.getElementsByClassName("selected") as HTMLCollectionOf<HTMLElement>;
    let reponseSelected = reponse[0].id;


    this.miahootPartie.questions.forEach((question) => {
      const partieRef = doc(db, "parties", this.codePartie);
      const questionRef= doc(partieRef,'questions', question.id!.toString());
      const reponseRef = doc(questionRef, 'reponses', reponseSelected);
      updateDoc(reponseRef, {
        nbVotes: arrayUnion(this.uid)
      });
    });

    /*
        const u =  firstValueFrom(this.auth.currentUser).then(user=>{
          const collParticipant = collection(this.us.getFirestore(), `parties/${this.codePartie}/questions/question/${this.miahootPartie.questions[this.currentIndex].id }/participants/${parseInt(reponseSelected)}` );
          addDoc(collParticipant, {
            uid: user?.uid??'',

          });


        }) ;*/
    document.getElementById('buttonConfirm')!.setAttribute('hidden', 'false');

  }



}
