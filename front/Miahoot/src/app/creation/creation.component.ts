import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {DsService} from "../service/ds.service";
import {AuthService} from "../service/auth.service";
import {Auth} from "@angular/fire/auth";
import {Miahoot, Reponse} from "../service/interfaces";

@Component({
  selector: 'app-creation',
  templateUrl: './creation.component.html',
  styleUrls: ['./creation.component.scss']
})
export class CreationComponent implements OnInit{
  nameMiahoot = 'Angular';
  reactiveForm !: FormGroup;
  formIsValid: boolean = true;
  miahootIsCreated : boolean = false;

  constructor(public ds:  DsService, private auth : Auth) {
  }

  //tableau rempli avec les question saisies par l'enseignant
  questions: any[] = [];

  openForm() {
    const form = document.getElementById('myForm');
    if (form) {
      form.style.display = 'block';
    }
  }
  closeForm() {
    const form = document.getElementById('myForm');
    if (form) {
      form.style.display = 'none';
    }
  }

  ngOnInit(): void {
    this.miahootIsCreated = false
    this.reactiveForm = new FormGroup({
      question : new FormControl(null,[Validators.required]),
      reponse1 : new FormControl(null,[Validators.required]),
      reponse2 : new FormControl(null,[Validators.required]),
      reponse3 : new FormControl(null),
      reponse4 : new FormControl(null),
      isValidReponse1 : new FormControl(null),
      isValidReponse2 : new FormControl(null),
      isValidReponse3 : new FormControl(null),
      isValidReponse4 : new FormControl(null)
    });


  }



  onSubmit(){
    if (this.reactiveForm.valid) {
      this.questions.push(this.reactiveForm.value);
      console.log(this.reactiveForm);

      this.closeForm();
      this.reactiveForm.reset();
    }else{
      this.formIsValid = false;
      throw new Error('Form is invalid');
    }

  }

  submitName(input : string){
    console.warn(input);
    this.nameMiahoot =input;

  }




  postMiahoot(miahoot: string) {
    let jsonmiahoot = JSON.parse(miahoot);
    //json to Miahoot object
    let miahootObj: Miahoot = {
      nom: jsonmiahoot.nom,
      questions: jsonmiahoot.questions
    }
    this.ds.createMiahoot(miahootObj)
  }


}
