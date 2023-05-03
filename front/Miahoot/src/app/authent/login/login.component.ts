import { Component, OnInit } from '@angular/core';
import { Auth, GoogleAuthProvider, User, authState, signInWithPopup, signOut } from '@angular/fire/auth';
import { Router } from '@angular/router';
import { BehaviorSubject, EMPTY, Observable } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  email : string = '';
  password : string = '';

  //constructor(private auth : AuthService) { }

  ngOnInit(): void {
  }

  login() {

    if(this.email == '') {
      alert('Please enter email');
      return;
    }

    if(this.password == '') {
      alert('Please enter password');
      return;
    }

    this.authS.login(this.email,this.password);
    
    this.email = '';
    this.password = '';

  }

  /*signInWithGoogle() {
    this.auth.googleSignIn();
  }*/
  
  public readonly user:  Observable<User | null> = EMPTY;
  bsIsAuth = new BehaviorSubject<boolean>(false);


  constructor(private auth: Auth, private router :Router, private authS: AuthService) {
    this.user = authState(this.auth); //ds.MiahootUser
  }

  async loginG(){
    this.bsIsAuth.next(true);
    const googleProvider = new GoogleAuthProvider();
    googleProvider.setCustomParameters({
      prompt: 'select_account'
    });
    try{
      await signInWithPopup(this.auth, googleProvider).then(()  => { 
        this.router.navigate(['enseignant']) })
    }catch(err){
      console.error("erreur de login")
    }
    this.bsIsAuth.next(false);
  }

  async logout(){
    return await signOut(this.auth);
  }

  toAccountConfig(){
    this.router.navigateByUrl("accountConfig")
  }

  
 
}

