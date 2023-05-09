import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {DataService} from "../service/data.service";
import {PresentationService} from "../service/presentation.service";
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "../service/auth.service";
import {Enseignant, Miahoot, Partie, Question} from "../service/interfaces";
import {collection, doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {DsService} from "../service/ds.service";
import {addDoc} from "@angular/fire/firestore";



@Component({
  selector: 'app-presentateur',
  templateUrl: './presentateur.component.html',
  styleUrls: ['./presentateur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentateurComponent implements OnInit{

  db = this.us.getFirestore();

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
    console.log(miahootSelected)
    let code = document.getElementById("codePartie") as HTMLInputElement;
  let uid = '';
    console.log(miahootSelected, code.value);

    //create a new "partie" in the firebase database
    //with the code and the miahoot selected
    //and the user who created the game
    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
      uid =user?.uid??'';
      let x = this.ds.getMiahootById(uid, parseInt(miahootSelected));
      console.log('ici: ')
      console.log(JSON.stringify(x))
      const docRef = doc(this.us.getFirestore(), `parties/${code.value}/` );

      const newCollectionMiahoots = doc(this.us.getFirestore(), `parties/${code.value}/miahoots/miahoot` );
      let nomMiahoot = x.then(miahoot =>{
        setDoc(newCollectionMiahoots, {
          //miahoot nom
          miahoot: miahoot
        });

        let nompartie= document.getElementById("nompartie") as HTMLInputElement;
        let tempval   = {codePartie: parseFloat(code.value), nom:nompartie.value}
        console.log(nompartie.value);
        console.log(uid);
        console.log(document.getElementById("nompartie") as HTMLInputElement)
        this.ds.createPartie(uid, parseInt(miahootSelected), tempval);
      });


    });

  }
}



