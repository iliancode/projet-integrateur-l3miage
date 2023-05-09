import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DsService} from "../service/ds.service";
import {Enseignant, Miahoot, Participant, Partie} from "../service/interfaces";
import {doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";



@Component({
  selector: 'app-participant',
  templateUrl: './participant.component.html',
  styleUrls: ['./participant.component.scss']
})
export class ParticipantComponent implements OnInit{

  pseudo = ""
  reactiveForm !: FormGroup;
  participantPartie : any[] = []
  message="";
  participant !: Participant;


  constructor(public as: AuthService, private router: Router,public ds:DsService,private us : UserService) {
  }
  // a modifier car il faut voir comment gerer le pseudo ...
  signInAnonymously(){
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
    })
  }

  onSubmit(){
    if(this.reactiveForm.valid){
      this.participantPartie.push(this.reactiveForm.value);
      console.log(this.reactiveForm);
      console.log(this.reactiveForm.get('codePartie')?.value);
      this.message=""

      //ajouterParticipantDansPartie(participant,this.reactiveForm.get('codePartie')?.value)
            //Post le pseudo et le code partie du participant



    }else{
      this.message="Veuillez remplir tous les champs"
    }

    /**
    newEnseignant(e: Enseignant) {

      const docRef = doc(this.us.getFirestore(), "enseignants", e.mail); // on utilise l'email comme ID du document
      setDoc(docRef, e)
        .then(() => {
          console.log("Enseignant enregistré avec succès sur Firestore !");
        })
        .catch((error) => {
          console.error("Erreur lors de l'enregistrement de l'enseignant sur Firestore : ", error);
        });

      this.ds.postE(e)
        .then(enseignant => console.log(enseignant))
        .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
    } **/
  }

  ajouterParticipantDansPartie(p : Participant, codeP : number ): void{
    const docRef = doc(this.us.getFirestore(), "enseignants", ); // on utilise l'email comme ID du document
    setDoc(docRef, p)
      .then(() => {
        console.log("Participant enregistré avec succès sur Firestore !");
      })
      .catch((error) => {
        console.error("Erreur lors de l'enregistrement de l'enseignant sur Firestore : ", error);
      });

  }


}
