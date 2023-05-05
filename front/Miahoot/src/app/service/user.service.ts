import { Injectable } from '@angular/core';
import { Auth, authState, user } from '@angular/fire/auth';
import {
  docData,
  getDoc,
  Firestore,
  FirestoreDataConverter,
} from '@angular/fire/firestore';
import { collection, doc, setDoc } from 'firebase/firestore';

import { Observable, throwError, of, switchMap } from 'rxjs';

interface EnseignantUser {
  pseudo : string,
  mail : string,
  mdp : string
}
const convEnseignant: FirestoreDataConverter<EnseignantUser> = {
  toFirestore: (EU) => EU,
  fromFirestore: (snap) => ({
    pseudo: snap.get('pseudo') ?? '',
    mail: snap.get('mail') ?? '',
    mdp : snap.get('mdp') ?? '',
  }),
};
@Injectable({
  providedIn: 'root'
})

export class UserService {


  constructor(private auth: Auth, private fireS: Firestore,private firestore : Firestore) {


  }

  getFirestore() {
    return this.firestore;
  }
}
