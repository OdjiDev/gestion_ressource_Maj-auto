import { Injectable, signal, computed } from '@angular/core';

export interface AuthUser {
  id?: number;
  username: string;
  email: string;
  role: string;
}

const STORAGE_KEY = 'auth_user';

@Injectable({ providedIn: 'root' })
export class AuthStore {
  private readonly _user = signal<AuthUser | null>(this.readFromStorage());
  readonly user = this._user.asReadonly();
  readonly isAuthenticated = computed(() => this._user() !== null);
  readonly role = computed(() => this._user()?.role ?? null);
  readonly email = computed(() => this._user()?.email ?? null);

  setUser(user: AuthUser): void {
    this._user.set(user);
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user));
  }
  clear(): void {
    this._user.set(null);
    localStorage.removeItem(STORAGE_KEY);
  }
  private readFromStorage(): AuthUser | null {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return null;
    try {
      return JSON.parse(raw);
    } catch {
      return null;
    }
  }
}
