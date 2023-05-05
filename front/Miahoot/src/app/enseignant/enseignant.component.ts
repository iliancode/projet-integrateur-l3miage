import { ChangeDetectionStrategy, Component } from '@angular/core';
import {firstValueFrom} from "rxjs";
import { DsService} from "../service/ds.service";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-enseignant',
  templateUrl: './enseignant.component.html',
  styleUrls: ['./enseignant.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EnseignantComponent {

  constructor(private ds :DsService, private auth : AuthService) {
  }
ngOnInit(): void {
  const u = firstValueFrom(this.auth.currentUser).then(user => console.log( user?.uid));

}
}
