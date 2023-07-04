import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NursetableComponent } from './nurselist.component';

describe('NursetableComponent', () => {
  let component: NursetableComponent;
  let fixture: ComponentFixture<NursetableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NursetableComponent]
    });
    fixture = TestBed.createComponent(NursetableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
