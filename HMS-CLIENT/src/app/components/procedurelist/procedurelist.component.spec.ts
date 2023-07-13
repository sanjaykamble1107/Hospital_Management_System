import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcedurelistComponent } from './procedurelist.component';

describe('ProcedurelistComponent', () => {
  let component: ProcedurelistComponent;
  let fixture: ComponentFixture<ProcedurelistComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProcedurelistComponent]
    });
    fixture = TestBed.createComponent(ProcedurelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
