import { TestBed } from '@angular/core/testing';

import { AffiliatedwithServiceService } from './affiliatedwith-service.service';

describe('AffiliatedwithServiceService', () => {
  let service: AffiliatedwithServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AffiliatedwithServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
