import { TestBed } from '@angular/core/testing';

import { LignedemandeService } from './lignedemande.service';

describe('LignedemandeService', () => {
  let service: LignedemandeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LignedemandeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
