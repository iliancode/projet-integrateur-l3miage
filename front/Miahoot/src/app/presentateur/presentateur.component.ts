import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {DataService} from "../service/data.service";
import {PresentationService} from "../service/presentation.service";
import {BehaviorSubject, firstValueFrom, Observable} from "rxjs";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "../service/auth.service";

interface Reponse {
  id?: string;
  label: string;
  estValide: boolean;
}

interface Question {
  id?: string;
  label: string;
  reponses: Reponse[];
}

interface Miahoot {
  id?: string;
  nom: string;
  questions: Question[];
}
interface Enseignant {
  pseudo : String,
  mail : String,
  mdp : String
}

@Component({
  selector: 'app-presentateur',
  templateUrl: './presentateur.component.html',
  styleUrls: ['./presentateur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentateurComponent implements OnInit{


  enseignant : Enseignant = <Enseignant>{pseudo: 'adil', mail:'lpb@gmail.com', mdp : 'trop fort'};
  miahoots: Miahoot[] = [];
  miahoot : Miahoot;
  public readonly question_courante: BehaviorSubject<Question | null>;
  indexQuestionCourante = 0;



  constructor(private ps : PresentationService, private auth: AuthService) {
    this.miahoot = this.miahoots[0];
    this.question_courante = new BehaviorSubject<Question | null>(null,);
  }

  questionSuivante() {
    this.indexQuestionCourante++;
    if (this.indexQuestionCourante < this.miahoot.questions.length) {
      this.question_courante.next(this.miahoot.questions[this.indexQuestionCourante]);
    }
  }

  ngOnInit() {

    const u =  firstValueFrom(this.auth.currentUser).then(user=>{

      this.ps.getMiahootsOfEnseignant(user?.uid??'vache')
      .then(miahoots => {
        this.miahoots = miahoots;
        this.miahoot = this.miahoots[0];
        this.question_courante.next(this.miahoot.questions[this.indexQuestionCourante]);
        console.log(this.miahoots);
        console.log(this.miahoot);
        console.log(this.question_courante);


      });

    }) ;

  }
  }

/**
  async ngOnInit() {

    this.ds.getEnseignant("adil@gmail.com")
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));


    this.ds.createEnseignant(this.enseignant)
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("Le post marche passssss"));

    this.miahoots = await this.ds.getMiahootsOfEnseignant('adil@gmail.com');
  console.log(this.miahoots);

  }
     **/

