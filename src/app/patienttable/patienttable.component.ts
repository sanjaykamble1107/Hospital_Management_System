import { Component, OnInit } from '@angular/core';
import { PatientServiceService } from '../service/Patient/patient-service.service';

@Component({
  selector: 'app-patienttable',
  templateUrl: './patienttable.component.html',
  styleUrls: ['./patienttable.component.css'],
})
export class PatienttableComponent implements OnInit {
  patientlist: any = [];
  constructor(public patientservice: PatientServiceService) {}
  ngOnInit(): void {
    this.patientservice
      .get()
      .subscribe((response: any) => (this.patientlist = [...response]));
  }
}
