import { TestBed } from '@angular/core/testing';

import { PhysicianServiceService } from './physician-service.service';

describe('PhysicianServiceService', () => {
  let service: PhysicianServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhysicianServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
