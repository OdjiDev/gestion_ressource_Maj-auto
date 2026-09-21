import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError(error => {
      switch (error.status) {
        case 401:
          console.warn('401 — Session expirée');
          break;
        case 403:
          console.warn('403 — Accès refusé');
          break;
        case 500:
          console.error('500 — Erreur serveur');
          break;
        case 0:
          console.error('Serveur inaccessible');
          break;
      }
      return throwError(() => error);
    })
  );
};
