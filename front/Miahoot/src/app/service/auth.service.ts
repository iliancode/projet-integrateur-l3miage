import { Injectable } from '@angular/core';
import {Auth, authState, GoogleAuthProvider} from '@angular/fire/auth';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  currentUser = authState(this.auth);
  
  constructor(private fireauth : AngularFireAuth, private router : Router, private auth : Auth) { }

  // login method
  login(email : string, password : string) {
    this.fireauth.signInWithEmailAndPassword(email,password).then( () => {
        localStorage.setItem('token','true');
        this.router.navigate(['enseignant']);
  }, err => {
        alert(err.message);
        this.router.navigate(['/login']);
    })
  }

  // register method
  register(email : string, password : string) {
    this.fireauth.createUserWithEmailAndPassword(email, password).then( () => {
      alert('Registration Successful');
      this.router.navigate(['/login']);
    }, err => {
      alert(err.message);
      this.router.navigate(['/register']);
    })
    
  }

  // sign out
  logout() {
    this.fireauth.signOut().then( () => {
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
    }, err => {
      alert(err.message);
    })
  }

   //sign in with google
   googleSignIn() {
    return this.fireauth.signInWithPopup(new GoogleAuthProvider).then(res => {

      this.router.navigate(['/enseignant']);
      localStorage.setItem('token',JSON.stringify(res.user?.uid));

    }, err => {
      alert(err.message);
    })
  }

  anonymousLogin(){
    this.fireauth.signInAnonymously()
      //sert juste de test pour voir si la connexion fonctionne
      .then(()=>console.log("connexion anonyme"))
      .catch(()=>console.log("erreur lors de la connexion anonyme"))
  }

}
