// import { PersonelDto } from './../../../../../classes/personel-dto';
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
// import { DepartementDto } from 'src/app/classes/departement-dto';
// import { PersonelDto } from 'src/app/classes/personel-dto';
// import { DepartementService } from 'src/app/services/departement.service';
// import { PersonelsService } from 'src/app/services/personel.service';

// @Component({
//   selector: 'app-create-departement',
//   templateUrl: './create-departement.component.html',
//   styleUrls: ['./create-departement.component.css']
// })
// export class CreateDepartementComponent {
//   formDepartement: any;
//   departementDto: DepartementDto= new DepartementDto();

//   message: any;
//   style: any;
//   formSubmitAttempt: boolean= false;
//   personelsDtos: PersonelDto[]=[];

//   constructor(private formBuilder: FormBuilder,
//               private departementService: DepartementService,
//               private personnelsService: PersonelsService,
//               ){
//                 this.formDepartement= this.formBuilder.group({
//                   code: [null, Validators.required],
//                   libelle: [null, Validators.required],

//                   });
//       // this.departementDto.enseignantDto.id=1;
//      // this.getAllpersonels();
//   }
//   //Envoi de données vers le serveur
//   submitForm(){
//     this.formSubmitAttempt= true;
//       this.addDepartement(this.departementDto);
//       // this.getAllEnseignants();
//   }

//   //envoi des données de differentes interfaces à la base de données
//   addDepartement(et: DepartementDto){
// //     if (this.formDepartement.valid) {
//     this.departementService.createDepartement(et).subscribe(
//       (data: DepartementDto) => {
//         this.departementDto= data;
//         this.showSuccessMessage('Departement enrégistré avec succès');
//         this.formDepartement.reset();
//         this.formSubmitAttempt= false;
//         return;
//       }
//     );
//   }else{
//     this.showErrorMessage('Verifiez vos données et renseignez les champs obligatoire avec un *')
//   }
//   }

//   showSuccessMessage(id: any) {
//     this.message = 'Opération réussie !\n '+id;
//     this.style= "alert alert-success";
//     setTimeout(() => {
//       this.message = '';
//     }, 5000);
//   }

//   showErrorMessage(id: any) {
//     this.message = 'Oops un probleme est survenu. Opération échouée !\n '+id;
//     this.style= "alert alert-danger";
//     setTimeout(() => {
//       this.message = '';
//     }, 5000);
//   }

//   //load enseignants form the forms
//   getAllpersonels(){
//     this.personnelsService.getPersonels().subscribe(
//       (data: PersonelsDto[]) => {
//         this.personelsDtos= data;
//       }
//     );
//   }
//   // verify if the formDepartement's fields are validated
//   isFieldValid(field: string) {
//     return (!this.formDepartement.get(field).valid && this.formDepartement.get(field).touched) ||
//     (this.formDepartement.get(field).untouched && this.formSubmitAttempt);
//   }

//   // display css style for the the fields that aren't validate
//   displayFieldCss(field: string) {
//     return {
//       'has-error': this.isFieldValid(field),
//       'has-feedback': this.isFieldValid(field)
//     };
//   }

//   // loops fields in ordeer to verify if all are ok
//   validateAllFormFields(formGroup: FormGroup) {         //{1}
//     Object.keys(formGroup.controls).forEach(field => {  //{2}
//       const control = formGroup.get(field);             //{3}
//       if (control instanceof FormControl) {             //{4}
//         control.markAsTouched({ onlySelf: true });
//       } else if (control instanceof FormGroup) {        //{5}
//         this.validateAllFormFields(control);            //{6}
//       }
//     });
//   }
// }
 // }}



 import { PersonelDto } from '../../../../../classes/personel-dto';
 import { Component, OnInit } from '@angular/core';
 import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
 import { DepartementDto } from 'src/app/classes/departement-dto';
 import { DepartementService } from 'src/app/services/departement.service';
 import { PersonelsService } from 'src/app/services/personel.service';




 @Component({
   selector: 'app-departement-create',
   templateUrl: './create-departement.component.html',
   styleUrls: ['./create-departement.component.css']
 })
 export class DepartementCreateComponent {
   formDepartement: any;
   departementDto: DepartementDto= new DepartementDto();

   message: any;
   style: any;
   formSubmitAttempt: boolean= false;
  personrelDtos: PersonelDto[]=[];

   constructor(private formBuilder: FormBuilder,
               private departementService: DepartementService,
               private personelService:PersonelsService,
               ){
                 this.formDepartement= this.formBuilder.group({
                   code: [null, Validators.required],
                   libelle: [null, Validators.required],

                   });
       // this.departementDto.enseignantDto.id=1;
       this.getAllEnseignants();
   }
   //Envoi de données vers le serveur
   submitForm(){
     this.formSubmitAttempt= true;
       this.addDepartement(this.departementDto);
       // this.getAllEnseignants();
   }

   //envoi des données de differentes interfaces à la base de données
   addDepartement(et: DepartementDto){
  //    if (this.formDepartement.valid) {
  //    this.departementService.addDepartement(et).subscribe(
  //      (data: DepartementDto) => {
  //        this.departementDto= data;
  //        this.showSuccessMessage('Departement enrégistré avec succès');
  //        this.formDepartement.reset();
  //        this.formSubmitAttempt= false;
  //        return;
  //      }
  //    );
  //  }else{
  //    this.showErrorMessage('Verifiez vos données et renseignez les champs obligatoire avec un *')
  //  }
   }

   showSuccessMessage(id: any) {
     this.message = 'Opération réussie !\n '+id;
     this.style= "alert alert-success";
     setTimeout(() => {
       this.message = '';
     }, 5000);
   }

   showErrorMessage(id: any) {
     this.message = 'Oops un probleme est survenu. Opération échouée !\n '+id;
     this.style= "alert alert-danger";
     setTimeout(() => {
       this.message = '';
     }, 5000);
   }

   //load enseignants form the forms
   getAllEnseignants(){
     this.personelService.getPersonels().subscribe(
       (data: PersonelDto[]) => {
         this.personrelDtos= data;
       }
     );
   }
   // verify if the formDepartement's fields are validated
   isFieldValid(field: string) {
     return (!this.formDepartement.get(field).valid && this.formDepartement.get(field).touched) ||
     (this.formDepartement.get(field).untouched && this.formSubmitAttempt);
   }

   // display css style for the the fields that aren't validate
   displayFieldCss(field: string) {
     return {
       'has-error': this.isFieldValid(field),
       'has-feedback': this.isFieldValid(field)
     };
   }

   // loops fields in ordeer to verify if all are ok
   validateAllFormFields(formGroup: FormGroup) {         //{1}
     Object.keys(formGroup.controls).forEach(field => {  //{2}
       const control = formGroup.get(field);             //{3}
       if (control instanceof FormControl) {             //{4}
         control.markAsTouched({ onlySelf: true });
       } else if (control instanceof FormGroup) {        //{5}
         this.validateAllFormFields(control);            //{6}
       }
     });
   }
 }
