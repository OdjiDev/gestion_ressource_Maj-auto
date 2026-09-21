import { Injectable, signal, effect } from '@angular/core';

export type Theme = 'light' | 'dark';
const THEME_KEY = 'app_theme';

@Injectable({ providedIn: 'root' })
export class ThemeService {
  readonly theme = signal<Theme>(this.readFromStorage());

  constructor() {
    effect(() => {
      const t = this.theme();
      document.documentElement.setAttribute('data-theme', t);
      localStorage.setItem(THEME_KEY, t);
    });
  }

  toggle(): void { this.theme.update(t => t === 'light' ? 'dark' : 'light'); }
  set(theme: Theme): void { this.theme.set(theme); }
  isDark(): boolean { return this.theme() === 'dark'; }

  private readFromStorage(): Theme {
    return (localStorage.getItem(THEME_KEY) as Theme | null) ?? 'light';
  }
}
