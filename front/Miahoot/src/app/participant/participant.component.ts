import {Component, OnInit} from '@angular/core';
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DsService} from "../service/ds.service";
import {Enseignant, Miahoot, Participant, Partie, Question} from "../service/interfaces";
import {doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {user} from "@angular/fire/auth";
import {firstValueFrom} from "rxjs";



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


  miahootP : Miahoot = {
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
        "reponses": []
      },
      {
        "id": 3,
        "label": "1+1 ? ?",
        "reponses": []
      }
    ]
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
        this.ajouterParticipantDansPartie(this.p)
      }) ;






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

  ajouterParticipantDansPartie(p : Participant ): void{


    const docRef = doc(this.us.getFirestore(), `participant/${p.uid??'vache'}`); // on utilise l'email comme ID du document
    setDoc(docRef, {
      pseudo: p.pseudo,
      partie : p.partie,
      uid : p.uid,

    })
      .then(() => {
        console.log("Participant enregistré avec succès sur Firestore !");
      })
      .catch((error) => {
        console.error("Erreur lors de l'enregistrement de l'enseignant sur Firestore : ", error);
      });

  }
  /**initiliaserParticipant(p : Participant) : void {
    const u =  firstValueFrom(this.as.currentUser).then(user=>{
      this.partieP.codePartie = this.reactiveForm.get('codePartie')?.value;
      this.p.uid = user?.uid ?? 'CC';
      this.p.partie = this.partieP;


    }) ;
  } **/


}

