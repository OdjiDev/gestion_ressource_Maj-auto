import { FournisseurService } from 'src/app/services/fournisseur.service';
import { Component, OnInit } from '@angular/core';
import { FactureDto } from 'src/app/classes/facture-dto';
import { FactureService } from 'src/app/services/facture.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { FournisseurDto } from 'src/app/classes/fournisseur-dto';
import { Router } from '@angular/router';
import { LignefactureDto } from 'src/app/classes/lignefacture-dto';
import { LignefactureService } from 'src/app/services/lignefacture.service';
import { ProduitService } from 'src/app/services/produit.service';
import { ProduitDto } from 'src/app/classes/produit-dto';

@Component({
  selector: 'app-create-facture',
  templateUrl: './create-facture.component.html',
  styleUrls: ['./create-facture.component.css'],
})
export class CreateFactureComponent implements OnInit {
  // Declaration des formulaires
  formFournisseur: any;
  formFacture: any;
  formLigneFacture: any;

  formFournisseurSubmitAttempt: boolean = false;
  formFactureSubmitAttempt: boolean = false;
  formLigneFactureSubmitAttempt: boolean = false;
  // declaration des variables
  fournisseurDto: FournisseurDto = new FournisseurDto();
  factureDto: FactureDto = new FactureDto();
  lignefactureDto: LignefactureDto = new LignefactureDto();

  message: any;
  style: any;

  // declaration des tableaux
  fournisseurDtos: FournisseurDto[] = [];
  produitDtos: ProduitDto[] = [];
  ligneFactureDtos: LignefactureDto[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private fournisseurService: FournisseurService,
    private factureService: FactureService,
    private ligneFactureService: LignefactureService,
    private produitService: ProduitService,
    private router: Router
  ) {
    this.buildFournisseurForm();
    this.buildFactureForm();
    this.buildLigneFactureForm();
  }
  // Lors que le component est initialiser
  ngOnInit(): void {
    // Les methodes qui doivent s'executer en premier
    this.getFournisseurs();
  }
  //Envoi de données vers le serveur
  submitForm() {
    this.formFactureSubmitAttempt = true;
    this.addFournisseur(this.fournisseurDto);
  }

  //envoi des données de differentes interfaces à la base de données
  addFournisseur(et: FournisseurDto) {
    if (this.formFournisseur.valid) {
      this.fournisseurService
        .addFournisseur(et)
        .subscribe((data: FournisseurDto) => {
          this.fournisseurDto = data;
          this.showSuccessMessage('Fournisseur enrégistré avec succès');
          this.formFournisseur.reset();
          this.formFournisseurSubmitAttempt = false;
          return;
        });
    } else {
      this.showErrorMessage(
        'Verifiez vos données et renseignez les champs obligatoire avec un *'
      );
    }
  }

  addFacture(et: FactureDto) {
    if (this.formFacture.valid) {
      this.factureService.addFacture(et).subscribe((data: FactureDto) => {
        this.factureDto = data;
        this.showSuccessMessage('Facture enrégistré avec succès');
        this.formFacture.reset();
        this.formFactureSubmitAttempt = false;
        return;
      });
    } else {
      this.showErrorMessage(
        'Verifiez vos données et renseignez les champs obligatoire avec un *'
      );
    }
  }

  addLigneFacture(et: LignefactureDto) {
    if (this.formLigneFacture.valid) {
      this.ligneFactureService
        .addLignefacture(et)
        .subscribe((data: LignefactureDto) => {
          this.lignefactureDto = data;
          this.showSuccessMessage('LigneFacture enrégistré avec succès');
          this.formLigneFacture.reset();
          this.formLigneFactureSubmitAttempt = false;
          return;
        });
    } else {
      this.showErrorMessage(
        'Verifiez vos données et renseignez les champs obligatoire avec un *'
      );
    }
  }
  // Chargement de tout les fournisseurs
  getProduits() {
    this.produitService.getProduits().subscribe((data: ProduitDto[]) => {
      this.produitDtos = data;
      console.log('Liste des produits chargé avec succès: ', this.produitDtos);
      return;
    });
  }
  // Chargement de tout les fournisseurs
  getFournisseurs() {
    this.fournisseurService
      .getFournisseurs()
      .subscribe((data: FournisseurDto[]) => {
        this.fournisseurDtos = data;
        console.log('Fournisseurs chargé avec succès: ', this.fournisseurDtos);
        return;
      });
  }
  showSuccessMessage(id: any) {
    this.message = 'Opération réussie !\n ' + id;
    this.style = 'alert alert-success';
    setTimeout(() => {
      this.message = '';
    }, 5000);
  }

  showErrorMessage(id: any) {
    this.message = 'Oops un probleme est survenu. Opération échouée !\n ' + id;
    this.style = 'alert alert-danger';
    setTimeout(() => {
      this.message = '';
    }, 5000);
  }
  // verify if the formFournisseur's fields are validated
  isFieldFournisseurValid(field: string) {
    return (
      (!this.formFournisseur.get(field).valid &&
        this.formFournisseur.get(field).touched) ||
      (this.formFournisseur.get(field).untouched &&
        this.formFournisseurSubmitAttempt)
    );
  }

  // display css style for the the fields that aren't validate
  displayFournisseurFieldCss(field: string) {
    return {
      'has-error': this.isFieldFournisseurValid(field),
      'has-feedback': this.isFieldFournisseurValid(field),
    };
  }
  // verify if the formFournisseur's fields are validated
  isFieldFactureValid(field: string) {
    return (
      (!this.formFacture.get(field).valid &&
        this.formFacture.get(field).touched) ||
      (this.formFacture.get(field).untouched && this.formFactureSubmitAttempt)
    );
  }

  // display css style for the the fields that aren't validate
  displayFactureFieldCss(field: string) {
    return {
      'has-error': this.isFieldFactureValid(field),
      'has-feedback': this.isFieldFactureValid(field),
    };
  }
  // verify if the formFacture's fields are validated
  isFieldLigneFactureValid(field: string) {
    return (
      (!this.formLigneFacture.get(field).valid &&
        this.formLigneFacture.get(field).touched) ||
      (this.formLigneFacture.get(field).untouched &&
        this.formLigneFactureSubmitAttempt)
    );
  }

  // display css style for the the fields that aren't validate
  displayLigneFactureFieldCss(field: string) {
    return {
      'has-error': this.isFieldLigneFactureValid(field),
      'has-feedback': this.isFieldLigneFactureValid(field),
    };
  }

  // loops fields in ordeer to verify if all are ok
  validateAllFormFields(formGroup: FormGroup) {
    //{1}
    Object.keys(formGroup.controls).forEach((field) => {
      //{2}
      const control = formGroup.get(field); //{3}
      if (control instanceof FormControl) {
        //{4}
        control.markAsTouched({ onlySelf: true });
      } else if (control instanceof FormGroup) {
        //{5}
        this.validateAllFormFields(control); //{6}
      }
    });
  }
  // Methodes pour la creation des formulaires
  buildFournisseurForm() {
    this.formFournisseur = this.formBuilder.group({
      prenom: [null, Validators.required],
      nom: [null, Validators.required],
      adresse: [null, Validators.required],
      mail: [null, Validators.required],
      numtel: [null, Validators.required],
    });
  }
  buildFactureForm() {
    this.formFacture = this.formBuilder.group({
      code: [null, Validators.required],
      datecommande: [null, Validators.required],
      fournisseurDto: [null, Validators.required],
    });
  }
  buildLigneFactureForm() {
    this.formLigneFacture = this.formBuilder.group({
      quantite: [null, Validators.required],
      total: [null, Validators.required],
      produitDto: [null, Validators.required],
      factureDtoDto: [null, Validators.required],
    });
  }
  // Pour rediriger vers la liste des factures
  goTofactureList() {
    this.router.navigate(['admin/listfacture']);
  }
}
