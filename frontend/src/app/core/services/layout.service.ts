import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LayoutService {
  readonly sidebarCollapsed = signal(false);
  readonly mobileMenuOpen = signal(false);
  readonly mobileSidebarOpen = signal(false);

  toggleSidebar(): void { this.sidebarCollapsed.update(v => !v); }
  toggleCollapse(): void { this.sidebarCollapsed.update(v => !v); }
  collapseSidebar(): void { this.sidebarCollapsed.set(true); }
  expandSidebar(): void { this.sidebarCollapsed.set(false); }

  toggleMobileMenu(): void { this.mobileMenuOpen.update(v => !v); }
  closeMobileMenu(): void { this.mobileMenuOpen.set(false); }
  openMobileMenu(): void { this.mobileMenuOpen.set(true); }

  toggleMobileSidebar(): void { this.mobileSidebarOpen.update(v => !v); }
  closeMobileSidebar(): void { this.mobileSidebarOpen.set(false); }
  openMobileSidebar(): void { this.mobileSidebarOpen.set(true); }
}
