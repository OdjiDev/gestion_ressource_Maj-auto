import { Pipe, PipeTransform } from '@angular/core';
import { formatCurrency } from '@core/utils';

@Pipe({ name: 'xof', standalone: true })
export class CurrencyXofPipe implements PipeTransform {
  transform(value: number | null | undefined): string {
    if (value == null) return '0 FCFA';
    return formatCurrency(value, 'FCFA');
  }
}
