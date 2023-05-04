import { ChangeDetectionStrategy, Component } from '@angular/core';
import {Router} from "@angular/router";
import { DsService} from "../service/ds.service";
import {Miahoot} from "../service/ds.service";

@Component({
  selector: 'app-concepteur',
  templateUrl: './concepteur.component.html',
  styleUrls: ['./concepteur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConcepteurComponent {

  constructor(public router : Router, private ds: DsService) {


  }

  ngOnInit(): void {
    const input = document.getElementById('input') as HTMLInputElement;
    const miahootInput = document.getElementById('miahoot') as HTMLInputElement;

    input.addEventListener('change', () => {
      const file = input.files![0];
      const reader = new FileReader();

      reader.onload = () => {
        miahootInput.value = reader.result as string;
      };

      reader.readAsText(file);
    });
  }


  // supprime dans la vue html et dans l'api
  deleteMiahoot(id : string) {
    const del = document.getElementById(id)
    if(del !== null){
      del.remove()
    }

  }

  postMiahoot(mail: string, miahoot: string) {
    let jsonmiahoot = JSON.parse(miahoot);
    //json to Miahoot object
    let miahootObj: Miahoot = {
      nom: jsonmiahoot.nom,
      questions: jsonmiahoot.questions
    }


    this.ds.postM(mail, miahootObj);
  }




}
