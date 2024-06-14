import { TestBed } from '@angular/core/testing';

import { PersonelsService } from './personel.service';

describe('PersonelsService', () => {
  let service: PersonelsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersonelsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
