import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatephysicianComponent } from './updatephysician.component';

describe('UpdatephysicianComponent', () => {
  let component: UpdatephysicianComponent;
  let fixture: ComponentFixture<UpdatephysicianComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdatephysicianComponent]
    });
    fixture = TestBed.createComponent(UpdatephysicianComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
