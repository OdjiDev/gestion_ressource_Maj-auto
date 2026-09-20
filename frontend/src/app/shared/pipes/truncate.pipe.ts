import { Pipe, PipeTransform } from '@angular/core';
import { truncate } from '@core/utils';

@Pipe({ name: 'truncate', standalone: true })
export class TruncatePipe implements PipeTransform {
  transform(value: string | null | undefined, maxLength: number = 50): string {
    if (!value) return '';
    return truncate(value, maxLength);
  }
}
