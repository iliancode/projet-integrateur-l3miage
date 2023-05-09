import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DsService} from "../service/ds.service";
import {Enseignant, Miahoot, Participant, Partie, Question} from "../service/interfaces";
import {collection, doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {user} from "@angular/fire/auth";
import {firstValueFrom} from "rxjs";
import {get} from "@angular/fire/database";
import {collectionGroup, getDoc, getDocs, where} from "@angular/fire/firestore";
import {query} from "@angular/animations";
import {initializeApp} from "@angular/fire/app";




@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.scss']
})
export class ParticipantComponent implements OnInit{


  islogged: boolean = false
  pseudo = ""
  reactiveForm !: FormGroup;
  participantPartie : any[] = []
  message="";
  snapCode : any;

  miahootP : Miahoot = {
    "id": 1,
    "nom": "Quizz Web",
    "questions": []
  };
  partieP : Partie = {
    codePartie: 0,
    nom: "miahooot",
    miahoot: this.miahootP
  }

  p : Participant = {
    pseudo : 'lyna',
    uid : 'jnfjsd234',
    partie : this.partieP
  }


  constructor(public as: AuthService, private router: Router,public ds:DsService,private us : UserService) {
  }

  // a modifier car il faut voir comment gerer le pseudo ...
  signInAnonymously(){
      this.islogged = true
      this.as.anonymousLogin()
  }
  //pour le deplacement vers la partie

  afterSignIn(){
    this.router.navigateByUrl('presentation')
  }


  ngOnInit() {
    this.reactiveForm = new FormGroup({
      pseudoParticipant : new FormControl(null,[Validators.required]),
      codePartie : new FormControl(null,[Validators.required])
    });


}



  onSubmit(){
    if(this.reactiveForm.valid){
      this.participantPartie.push(this.reactiveForm.value);
      this.message=""

      let x : string | undefined ='';
      const u =  firstValueFrom(this.as.currentUser).then(user=>{

        this.partieP.codePartie = this.reactiveForm.get('codePartie')?.value;
        this.p.uid = user?.uid ?? 'CC';
        this.p.pseudo = this.reactiveForm.get('pseudoParticipant')?.value;
        this.p.partie = this.partieP;
        x = user?.uid;
        this.ajouterParticipantDansPartie(this.p,this.reactiveForm.get('codePartie')?.value)
      }) ;
    }else{
      this.message="Veuillez remplir tous les champs"
    }

  }

   async ajouterParticipantDansPartie(p: Participant, codeP: number): Promise<void> {


    const w = await this.verificationCodePartie(codeP).then(estPresent => {
      console.log(estPresent);
      if (estPresent) {
        const docRef = doc(this.us.getFirestore(), `partie/${codeP ?? 'Flop'}/participant/${p.uid ?? 'pas de uid'}`);
       /**setDoc(docRef, {
          pseudo: p.pseudo,
          uid: p.uid,

        }).then(() => {
          console.log("Participant enregistré avec succès sur Firestore !");
        })*/
       console.log("Participant enregistré avec succès sur Firestore !");
      } else {
        console.log("Le code partie n'existe pas :p ");
      }
    })
  }

  async verificationCodePartie(codeP : number): Promise<boolean>{
    let estPresent : boolean = false;
    const querySnapshot = await getDocs(collection(this.us.getFirestore(), "parties/32000/miahoots/miahoot"));
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.id, " => ");
    });
    return estPresent;
  }
}

