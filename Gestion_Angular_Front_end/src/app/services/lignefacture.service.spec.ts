import { TestBed } from '@angular/core/testing';

import { LignefactureService } from './lignefacture.service';

describe('LignefactureService', () => {
  let service: LignefactureService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LignefactureService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
