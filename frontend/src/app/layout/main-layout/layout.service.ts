import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LayoutService {
  /** Sidebar ouverte sur mobile ? */
  readonly mobileSidebarOpen = signal(false);

  /** Sidebar réduite (icônes seules) sur desktop ? */
  readonly sidebarCollapsed = signal(false);

  /** Groupes de menus ouverts */
  readonly openGroups = signal<string[]>(['gestion']);

  toggleMobileSidebar(): void {
    this.mobileSidebarOpen.update(v => !v);
  }

  closeMobileSidebar(): void {
    this.mobileSidebarOpen.set(false);
  }

  toggleCollapse(): void {
    this.sidebarCollapsed.update(v => !v);
  }

  toggleGroup(group: string): void {
    this.openGroups.update(groups =>
      groups.includes(group) ? groups.filter(g => g !== group) : [...groups, group]
    );
  }

  isGroupOpen(group: string): boolean {
    return this.openGroups().includes(group);
  }
}
