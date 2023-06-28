import { TestBed } from '@angular/core/testing';

import { NurseServiceService } from './nurse-service.service';

describe('NurseServiceService', () => {
  let service: NurseServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NurseServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
