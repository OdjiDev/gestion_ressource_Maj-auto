import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LayoutService {

  readonly sidebarCollapsed = signal(false);
  readonly mobileMenuOpen = signal(false);
  readonly mobileSidebarOpen = signal(false);

  private readonly openGroups = signal<Set<string>>(new Set(['gestion']));

  // === Sidebar ===
  toggleSidebar(): void { this.sidebarCollapsed.update(v => !v); }
  toggleCollapse(): void { this.sidebarCollapsed.update(v => !v); }
  collapseSidebar(): void { this.sidebarCollapsed.set(true); }
  expandSidebar(): void { this.sidebarCollapsed.set(false); }

  // === Mobile ===
  toggleMobileMenu(): void { this.mobileMenuOpen.update(v => !v); }
  closeMobileMenu(): void { this.mobileMenuOpen.set(false); }
  openMobileMenu(): void { this.mobileMenuOpen.set(true); }

  toggleMobileSidebar(): void { this.mobileSidebarOpen.update(v => !v); }
  closeMobileSidebar(): void { this.mobileSidebarOpen.set(false); }
  openMobileSidebar(): void { this.mobileSidebarOpen.set(true); }

  // === Groupes ===
  isGroupOpen(groupId: string): boolean {
    return this.openGroups().has(groupId);
  }

  toggleGroup(groupId: string): void {
    this.openGroups.update(groups => {
      const next = new Set(groups);
      if (next.has(groupId)) {
        next.delete(groupId);
      } else {
        next.add(groupId);
      }
      return next;
    });
  }

  openGroup(groupId: string): void {
    this.openGroups.update(g => new Set([...g, groupId]));
  }

  closeGroup(groupId: string): void {
    this.openGroups.update(g => {
      const next = new Set(g);
      next.delete(groupId);
      return next;
    });
  }

  closeAllGroups(): void {
    this.openGroups.set(new Set());
  }
}
