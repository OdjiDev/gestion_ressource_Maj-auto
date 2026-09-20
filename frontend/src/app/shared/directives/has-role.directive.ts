import { Directive, Input, TemplateRef, ViewContainerRef, inject, effect } from '@angular/core';
import { AuthService } from '@core/auth';

@Directive({ selector: '[appHasRole]', standalone: true })
export class HasRoleDirective {
  private readonly tpl = inject(TemplateRef<unknown>);
  private readonly vcr = inject(ViewContainerRef);
  private readonly auth = inject(AuthService);
  private roles: string[] = [];
  private isVisible = false;

  @Input() set appHasRole(roles: string | string[]) {
    this.roles = Array.isArray(roles) ? roles : [roles];
    this.updateView();
  }

  constructor() {
    effect(() => {
      this.auth.isLoggedIn();
      this.updateView();
    });
  }

  private updateView(): void {
    const hasRole = this.auth.hasRole(this.roles);
    if (hasRole && !this.isVisible) {
      this.vcr.createEmbeddedView(this.tpl);
      this.isVisible = true;
    } else if (!hasRole && this.isVisible) {
      this.vcr.clear();
      this.isVisible = false;
    }
  }
}
