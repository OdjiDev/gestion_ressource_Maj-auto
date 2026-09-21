import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { routes } from './app.routes';
import { jwtInterceptor, refreshInterceptor, errorInterceptor } from '@core/auth';
import { loadingInterceptor } from '@core/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        jwtInterceptor,
        errorInterceptor,
        refreshInterceptor,
        loadingInterceptor
      ])
    )
  ]
};
