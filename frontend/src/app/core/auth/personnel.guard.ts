import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

export const PersonnelGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const role = authService.getRole();

  if (role === 'PERSONNEL') {
    return true;
  }

  router.navigate(['/dashboard']);
  return false;
};
