import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const adminGuard: CanActivateFn = () => {
  const router = inject(Router);
  const userStr = localStorage.getItem('auth_user');

  if (!userStr) {
    router.navigate(['/login']);
    return false;
  }

  const user = JSON.parse(userStr);
  if (user.role === 'ADMIN') {
    return true;
  }

  router.navigate(['/']);
  return false;
};
