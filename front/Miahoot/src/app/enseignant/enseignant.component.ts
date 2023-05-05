import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {firstValueFrom, timeout} from "rxjs";
import { DsService} from "../service/ds.service";
import {AuthService} from "../service/auth.service";
import {doc, setDoc} from "firebase/firestore";
import {UserService} from "../service/user.service";
import {Enseignant} from "../service/interfaces";
import {User} from "@angular/fire/auth";

@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EnseignantComponent implements OnInit{

e:Enseignant = {uid:'', mail:'', mdp:'', pseudo:''};
  tab:any = this.ds.envoyerUser()

  constructor(private ds :DsService, private auth : AuthService, private us:UserService) {
  }
  ngOnInit(): void {

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{
      this.e.uid = user?.uid;
      this.e.mail = this.tab[0];
      this.e.pseudo = this.tab[1];
      this.e.mdp = this.tab[2];

      this.newEnseignant(this.e);

    }) ;

  }



  newEnseignant(e: Enseignant) {

    const docRef = doc(this.us.getFirestore(), `enseignants/${e.uid??'vache'}`); // on utilise l'email comme ID du document
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
  }

}
