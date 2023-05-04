import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {DsService} from "../service/ds.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-presentateur',
  templateUrl: './presentateur.component.html',
  styleUrls: ['./presentateur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentateurComponent implements OnInit{
  form: any;

  constructor(private ds : DsService) {
     }

  ngOnInit() : void {
    this.ds.get("enseignants", "adil@gmail.com")
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));


  }




}
