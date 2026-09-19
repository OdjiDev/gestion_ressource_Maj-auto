import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '@core/auth';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  private authService = inject(AuthService);
  private router = inject(Router);

  email = signal('');
  password = signal('');
  nom = signal('');
  prenom = signal('');
  error = signal<string | null>(null);
  loading = signal(false);

  onSubmit(): void {
    if (!this.email() || !this.password() || !this.nom()) {
      this.error.set('Email, mot de passe et nom obligatoires');
      return;
    }

    if (this.password().length < 8) {
      this.error.set('Le mot de passe doit contenir au moins 8 caractères');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.authService.register({
      email: this.email(),
      password: this.password(),
      role: 'USER'
    }).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['dashboard']);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.status === 409
          ? 'Cet email est déjà utilisé'
          : 'Erreur lors de l  inscription');
      }
    });
  }
}
