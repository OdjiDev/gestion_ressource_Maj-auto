import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';

export const gestionnaireGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const role = authService.getRole();

  if (role === 'ADMIN' || role === 'GESTIONNAIRE') {
    return true;
  }

  router.navigate(['/dashboard']);
  return false;
};
