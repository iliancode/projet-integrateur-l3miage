import { Component, ChangeDetectionStrategy, Optional } from '@angular/core';
import { Auth, authState, signInAnonymously, signOut, User, GoogleAuthProvider, signInWithPopup } from '@angular/fire/auth';
import { BehaviorSubject, EMPTY, map, Observable, of, Subscription, switchMap } from 'rxjs';
import { Router } from '@angular/router';
import { AuthService } from './service/auth.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class AppComponent {
  title = 'Miahoot';
  public readonly user:  Observable<User | null> = EMPTY;
  bsIsAuth = new BehaviorSubject<boolean>(false);

  constructor(private auth: Auth, private router :Router , public authS: AuthService) {
    this.user = authState(this.auth); //ds.MiahootUser
  }

  register() {
    this.authS.logout();
  }

  async login(){
    this.bsIsAuth.next(true);
    const googleProvider = new GoogleAuthProvider();
    googleProvider.setCustomParameters({
      prompt: 'select_account'
    });
    try{
      await signInWithPopup(this.auth, googleProvider);
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

  logoutS(){
    this.authS.logout();
  }

  
}

