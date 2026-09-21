import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { environment } from '@env/environment';

export interface LoginRequest { email: string; password: string; }
export interface RegisterRequest { email: string; password: string; role?: string; personelId?: number; }
export interface AuthResponse {
  token: string; refreshToken?: string; type: string;
  email: string; role?: string; expiresIn?: number;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly API = `${environment.baseURL}/auth`;
  private readonly TOKEN_KEY = 'auth_token';
  private readonly REFRESH_KEY = 'auth_refresh_token';
  private readonly USER_KEY = 'auth_user';

  private readonly http = inject(HttpClient);
  private readonly router = inject(Router);

  readonly isLoggedIn = signal<boolean>(this.hasToken());

  login(req: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API}/login`, req)
      .pipe(tap(res => this.storeSession(res)));
  }

  register(req: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API}/register`, req)
      .pipe(tap(res => this.storeSession(res)));
  }

  refresh(): Observable<AuthResponse> {
    const refreshToken = this.getRefreshToken();
    return this.http.post<AuthResponse>(`${this.API}/refresh`, { refreshToken })
      .pipe(tap(res => this.storeSession(res)));
  }

  logout(): void {
    const refreshToken = this.getRefreshToken();
    if (refreshToken) {
      this.http.post(`${this.API}/logout`, { refreshToken }).subscribe({ error: () => {} });
    }
    this.clearSession();
    this.router.navigate(['/login']);
  }

  getToken(): string | null { return localStorage.getItem(this.TOKEN_KEY); }
  getRefreshToken(): string | null { return localStorage.getItem(this.REFRESH_KEY); }
  getEmail(): string | null {
    const user = localStorage.getItem(this.USER_KEY);
    return user ? JSON.parse(user).email : null;
  }
  getRole(): string | null {
    const user = localStorage.getItem(this.USER_KEY);
    return user ? JSON.parse(user).role : null;
  }
  hasRole(roles: string[]): boolean {
    const role = this.getRole();
    return role !== null && roles.includes(role);
  }

  private hasToken(): boolean { return !!localStorage.getItem(this.TOKEN_KEY); }

  private storeSession(res: AuthResponse): void {
    localStorage.setItem(this.TOKEN_KEY, res.token);
    if (res.refreshToken) localStorage.setItem(this.REFRESH_KEY, res.refreshToken);
    localStorage.setItem(this.USER_KEY, JSON.stringify({
      email: res.email, role: res.role || 'USER'
    }));
    this.isLoggedIn.set(true);
  }

  private clearSession(): void {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.REFRESH_KEY);
    localStorage.removeItem(this.USER_KEY);
    this.isLoggedIn.set(false);
  }
}
