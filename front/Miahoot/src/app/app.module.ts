
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { initializeApp,provideFirebaseApp } from '@angular/fire/app';
import { environment } from '../environments/environment';
import { provideAuth,getAuth } from '@angular/fire/auth';
import { provideFirestore,getFirestore } from '@angular/fire/firestore';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { AccountConfigComponent } from './account-config/account-config.component';
import { AccueilComponent } from './accueil/accueil.component';
import {MatCardModule} from "@angular/material/card";
import { EnseignantComponent } from './enseignant/enseignant.component';
import { ConcepteurComponent } from './concepteur/concepteur.component';
import { PresentateurComponent } from './presentateur/presentateur.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { ParticipantComponent } from './participant/participant.component';
import { PseudoComponent } from './pseudo/pseudo.component';
import { PresentationComponent } from './presentation/presentation.component';

import { HttpClientModule } from '@angular/common/http'
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AngularFireModule } from '@angular/fire/compat';
import { LoginComponent } from './authent/login/login.component';
import { RegisterComponent } from './authent/register/register.component';
import { CreationComponent } from './creation/creation.component'

@NgModule({
  declarations: [
    AppComponent,
    AccountConfigComponent,
    AccueilComponent,
    EnseignantComponent,
    ConcepteurComponent,
    PresentateurComponent,
    ConnexionComponent,
    ParticipantComponent,
    PseudoComponent,
    PresentationComponent,
    LoginComponent,
    RegisterComponent,
    CreationComponent
  ],
    imports: [
        HttpClientModule,
        BrowserModule,
        AppRoutingModule,
        MatToolbarModule,
        MatMenuModule,
        MatIconModule,
        MatButtonModule,
        MatInputModule,
        MatFormFieldModule,
        MatProgressSpinnerModule,
        MatCheckboxModule,
        provideFirebaseApp(() => initializeApp(environment.firebase)),
        provideAuth(() => getAuth()),
        provideFirestore(() => getFirestore()),
        BrowserAnimationsModule,
        MatCardModule,
        AngularFireModule.initializeApp(environment.firebase),
        FormsModule,
        ReactiveFormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {

}
