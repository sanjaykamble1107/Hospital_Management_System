import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatienttableComponent } from './patienttable.component';

describe('PatienttableComponent', () => {
  let component: PatienttableComponent;
  let fixture: ComponentFixture<PatienttableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PatienttableComponent]
    });
    fixture = TestBed.createComponent(PatienttableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
