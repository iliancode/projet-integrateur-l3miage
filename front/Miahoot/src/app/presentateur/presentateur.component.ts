import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {DsService} from "../service/ds.service";

@Component({
  selector: 'app-presentateur',
  templateUrl: './presentateur.component.html',
  styleUrls: ['./presentateur.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PresentateurComponent implements OnInit{

  constructor(private ds : DsService) {}

  ngOnInit() : void {
    this.ds.getEnseignant("adil@gmail.com")
      .then(enseignant => console.log(enseignant))
      .catch(erreur =>console.log("pas d'enseignant trouve avec cet email"));
  }


}
