import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { BehaviorSubject, catchError, filter, switchMap, take, throwError } from 'rxjs';
import { AuthService } from './auth.service';

let isRefreshing = false;
const refreshTokenSubject = new BehaviorSubject<string | null>(null);

export const refreshInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(AuthService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      const isAuthError = error.status === 401;
      const isRefreshCall = req.url.includes('/auth/refresh');
      const isLoginCall = req.url.includes('/auth/login') || req.url.includes('/auth/register');

      if (!isAuthError || isRefreshCall || isLoginCall) return throwError(() => error);

      if (isRefreshing) {
        return refreshTokenSubject.pipe(
          filter(token => token !== null),
          take(1),
          switchMap(() => next(req))
        );
      }

      isRefreshing = true;
      refreshTokenSubject.next(null);

      return auth.refresh().pipe(
        switchMap(res => {
          isRefreshing = false;
          refreshTokenSubject.next(res.token);
          return next(req);
        }),
        catchError(refreshError => {
          isRefreshing = false;
          refreshTokenSubject.next(null);
          auth.logout();
          return throwError(() => refreshError);
        })
      );
    })
  );
};
