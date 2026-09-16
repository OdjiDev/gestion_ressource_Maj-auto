import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '@app/core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  private authService = inject(AuthService);
  private router = inject(Router);

  email = signal('');
  password = signal('');
  error = signal<string | null>(null);
  loading = signal(false);

  onSubmit(): void {
    if (!this.email() || !this.password()) {
      this.error.set('Email et mot de passe obligatoires');
      return;
    }

    this.loading.set(true);
    this.error.set(null);

    this.authService.login({
      email: this.email(),
      password: this.password()
    }).subscribe({
      next: () => {
        this.loading.set(false);
        this.router.navigate(['/admin/categories']);
      },
      error: (err) => {
        this.loading.set(false);
        this.error.set(err.status === 401
          ? 'Email ou mot de passe incorrect'
          : 'Erreur de connexion');
      }
    });
  }
}
