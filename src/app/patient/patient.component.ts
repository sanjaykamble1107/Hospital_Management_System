import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  addPatient = (formData: any) => {
    console.log(formData);
  };
}
