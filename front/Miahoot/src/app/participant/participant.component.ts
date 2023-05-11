import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DsService} from "../service/ds.service";
import {Enseignant, Miahoot, Participant, Partie, Question} from "../service/interfaces";
import {collection, doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {firstValueFrom} from "rxjs";
import {collectionGroup, getDoc, getDocs, getFirestore, query, where} from "@angular/fire/firestore";
import {db} from "../../environments/test";
import { environment } from "../../environments/environment";
import {initializeApp, provideFirebaseApp} from "@angular/fire/app";


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
  messageErreur=""

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

  uuid = "";
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
        this.uuid = x ?? '';
        this.ajouterParticipantDansPartie(this.p,this.reactiveForm.get('codePartie')?.value)
      }) ;
    }else{
      this.message="Veuillez remplir tous les champs"
    }

  }

  async ajouterParticipantDansPartie(p: Participant, codeP: number): Promise<void> {

    let pseudoParticipant = document.getElementById('pseudoParticipant') as HTMLInputElement;
    const w = await this.verificationCodePartie(codeP).then(estPresent => {
      console.log(estPresent);
      if (estPresent) {
        const docRef = doc(this.us.getFirestore(), `parties/${codeP ?? 'Flop'}/participants/${p.uid ?? 'pas de uid'}`);
        setDoc(docRef, {
          pseudo: pseudoParticipant.value,
          uid: p.uid,

        }).then(() => {

          window.location.href = '/presentation/'+codeP;
          console.log("Participant enregistré avec succès sur Firestore !");
          this.messageErreur="";
        })
        
      } else {
        //alert("le code partie n'existe pas veuillez entrer un code valide");
        console.log("Le code partie n'existe pas :p ");
        this.messageErreur="le code partie n'existe pas veuillez entrer un code valide"
      }
    })
  }

  async verificationCodePartie(codeP : number): Promise<boolean>{


    console.log("CODE P :"+codeP.toString())
    let estPresent : boolean = false;
    const querySnapshot = await getDocs(collection(this.us.getFirestore(), "parties"));
    querySnapshot.forEach((doc) => {
      // doc.data() is never undefined for query doc snapshots
      console.log(doc.id, " => ");
      if(doc.id == codeP.toString()){
        estPresent = true;
      }
    });
    return estPresent;
  }
}
