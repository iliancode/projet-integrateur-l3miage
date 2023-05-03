import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EnseignantComponent } from './enseignant/enseignant.component';
import { ConcepteurComponent } from './concepteur/concepteur.component';
import { PresentateurComponent } from './presentateur/presentateur.component';
import { AccueilComponent } from './accueil/accueil.component';
import { AccountConfigComponent } from './account-config/account-config.component';
import { ConnexionComponent } from './connexion/connexion.component';
import {PresentationComponent} from "./presentation/presentation.component";
import { LoginComponent } from './authent/login/login.component';
import { RegisterComponent } from './authent/register/register.component';
import {ParticipantComponent} from "./participant/participant.component";
import {CreationComponent} from "./creation/creation.component";

const routes: Routes = [
  {path: '', component: AccueilComponent},
  {path: 'login', component: LoginComponent },
  {path: 'register', component: RegisterComponent },
  {path: 'participant', component: ParticipantComponent},
  {path: 'accountConfig', component: AccountConfigComponent },
  {path: 'enseignant', component: EnseignantComponent},
  {path: 'connexion', component: ConnexionComponent},
  {path: 'concepteur', component: ConcepteurComponent},
  {path: 'presentateur', component: PresentateurComponent},
  {path: 'presentation', component: PresentationComponent},
  {path: "creation", component: CreationComponent},
  {path: '**',component: AccueilComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports:[RouterModule]
})


export class AppRoutingModule { }
