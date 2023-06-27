import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PatientServiceService } from '../service/Patient/patient-service.service';
@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
})
export class PatientComponent {
  patientForm = new FormGroup({
    ssn: new FormControl(),
    name: new FormControl(''),
    address: new FormControl(''),
    phone: new FormControl(),
    insuranceID: new FormControl(),
    pcp: new FormControl(),
  });

  constructor(public patientService: PatientServiceService) { }
  addPatient = (data: any) => {
    this.patientService.save(data).subscribe((response: any) => console.log(response));
  };
}
