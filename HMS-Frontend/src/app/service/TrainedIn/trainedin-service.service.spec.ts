import { TestBed } from '@angular/core/testing';

import { TrainedinServiceService } from './trainedin-service.service';

describe('TrainedinServiceService', () => {
  let service: TrainedinServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TrainedinServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
