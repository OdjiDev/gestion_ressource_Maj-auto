import { Injectable, signal } from '@angular/core';

export type NotificationType = 'success' | 'error' | 'info' | 'warning';

export interface Notification {
  id: number;
  type: NotificationType;
  message: string;
  duration: number;
}

@Injectable({ providedIn: 'root' })
export class NotificationService {
  private counter = 0;
  readonly notifications = signal<Notification[]>([]);

  success(message: string, duration = 3000): void { this.push('success', message, duration); }
  error(message: string, duration = 5000): void { this.push('error', message, duration); }
  info(message: string, duration = 3000): void { this.push('info', message, duration); }
  warning(message: string, duration = 4000): void { this.push('warning', message, duration); }

  dismiss(id: number): void {
    this.notifications.update(list => list.filter(n => n.id !== id));
  }
  clear(): void { this.notifications.set([]); }

  private push(type: NotificationType, message: string, duration: number): void {
    const id = ++this.counter;
    this.notifications.update(list => [...list, { id, type, message, duration }]);
    if (duration > 0) setTimeout(() => this.dismiss(id), duration);
  }
}
