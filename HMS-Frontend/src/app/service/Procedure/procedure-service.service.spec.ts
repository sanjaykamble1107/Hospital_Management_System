import { TestBed } from '@angular/core/testing';

import { ProcedureServiceService } from './procedure-service.service';

describe('ProcedureServiceService', () => {
  let service: ProcedureServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProcedureServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
