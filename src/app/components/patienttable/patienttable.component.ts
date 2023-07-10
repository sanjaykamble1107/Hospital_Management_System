import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth/auth.service';
import { PatientServiceService } from 'src/app/service/Patient/patient-service.service';

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
