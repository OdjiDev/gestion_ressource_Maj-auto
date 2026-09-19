import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

export const comptableGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const role = authService.getRole();

  if (role === 'ADMIN' || role === 'COMPTABLE') {
    return true;
  }

  router.navigate(['/dashboard']);
  return false;
};
