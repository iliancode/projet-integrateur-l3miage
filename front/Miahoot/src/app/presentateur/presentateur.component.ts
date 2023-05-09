import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {DataService} from "../service/data.service";
import {PresentationService} from "../service/presentation.service";
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "../service/auth.service";
import {Enseignant, Miahoot, Question} from "../service/interfaces";
import {doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {DsService} from "../service/ds.service";



@Component({
  selector: 'app-presentateur',
  templateUrl: './presentateur.component.html',
  styleUrls: ['./presentateur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentateurComponent implements OnInit{


  enseignant : Enseignant = <Enseignant>{pseudo: 'adil', mail:'lpb@gmail.com', mdp : 'trop fort'};
  miahoots: Miahoot[] = [];
  miahoot : Miahoot;
  public readonly liste_miahoots: BehaviorSubject<Miahoot[] | null>;
  public readonly question_courante: BehaviorSubject<Question | null>;
  indexQuestionCourante = 0;
  selectedMiahoot: any = null;

  constructor(private ps : PresentationService, private auth: AuthService, private us:UserService, public ds: DsService) {
    this.miahoot = this.miahoots[0];
    this.question_courante = new BehaviorSubject<Question | null>(null,);
    this.liste_miahoots = new BehaviorSubject<Miahoot[] | null>(null,);
  }

  questionSuivante() {
    this.indexQuestionCourante++;
    if (this.indexQuestionCourante < this.miahoot.questions.length) {
      this.question_courante.next(this.miahoot.questions[this.indexQuestionCourante]);
    }
  }

  ngOnInit() {

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{

      this.ps.getMiahootsOfEnseignant(user?.uid??'vache')
      .then(miahoots => {
        this.miahoots = miahoots;
        this.miahoot = this.miahoots[0];
        this.liste_miahoots.next(miahoots);
        this.question_courante.next(this.miahoot.questions[this.indexQuestionCourante]);

      });

    }) ;

  }

  onButtonClick(miahoot: Miahoot){
    this.selectedMiahoot = miahoot;

  }

  protected readonly onclick = onclick;


  createGame(){
    let miahoot= document.getElementsByClassName("selected") as HTMLCollectionOf<HTMLElement>;
    let miahootSelected = miahoot[0].id;
    let code = document.getElementById("codePartie") as HTMLInputElement;
  let uid = '';
    console.log(miahootSelected, code.value);

    //create a new "partie" in the firebase database
    //with the code and the miahoot selected
    //and the user who created the game
    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
      uid =user?.uid??'vache';
    });
    let x = this.ds.getMiahootById(uid, parseInt(miahootSelected));

    const docRef = doc(this.us.getFirestore(), `partie/${code.value}/` );

    setDoc(docRef,
      {
        code: code.value
  }

    ).then(() => {
      console.log("Partie enregistrée avec succès sur Firestore !");
    })
  .catch((error) => {
      console.error("Erreur lors de l'enregistrement de la partie sur Firestore : ", error);
    });
  }
    /*const docRef = doc(this.us.getFirestore(), `enseignants/${e.uid??'vache'}`); // on utilise l'email comme ID du document
    setDoc(docRef, {
      pseudo: e.pseudo,
      mail: e.mail,
      mdp: e.mdp,
    })
      .then(() => {
        console.log("Enseignant enregistré avec succès sur Firestore !");
      })
      .catch((error) => {
        console.error("Erreur lors de l'enregistrement de l'enseignant sur Firestore : ", error);
      });

    this.ds.postE(e)
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
    console.log("ici")
    */



}

/**
  async ngOnInit() {

    this.ds.getEnseignant("adil@gmail.com")
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));


    this.ds.createEnseignant(this.enseignant)
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("Le post marche passssss"));

    this.miahoots = await this.ds.getMiahootsOfEnseignant('adil@gmail.com');
  console.log(this.miahoots);

  }
     **/

