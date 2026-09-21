import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class StorageService {
  set<T>(key: string, value: T): void {
    try { localStorage.setItem(key, JSON.stringify(value)); }
    catch (e) { console.error('StorageService.set', e); }
  }
  get<T>(key: string): T | null {
    try {
      const raw = localStorage.getItem(key);
      return raw ? JSON.parse(raw) as T : null;
    } catch (e) { console.error('StorageService.get', e); return null; }
  }
  remove(key: string): void { localStorage.removeItem(key); }
  clear(): void { localStorage.clear(); }
  has(key: string): boolean { return localStorage.getItem(key) !== null; }
}
