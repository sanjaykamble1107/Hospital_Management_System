import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainedInComponent } from './trained-in.component';

describe('TrainedInComponent', () => {
  let component: TrainedInComponent;
  let fixture: ComponentFixture<TrainedInComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TrainedInComponent]
    });
    fixture = TestBed.createComponent(TrainedInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
