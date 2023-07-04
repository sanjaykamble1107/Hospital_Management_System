import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PatientServiceService } from '../service/Patient/patient-service.service';
import { PhysicianServiceService } from '../service/Physician/physician-service.service';
@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
})
export class PatientComponent implements OnInit {
  patientForm = new FormGroup({
    ssn: new FormControl(),
    name: new FormControl(''),
    address: new FormControl(''),
    phone: new FormControl(),
    insuranceID: new FormControl(),
    pcp: new FormControl(),
  });



  constructor(public patientService: PatientServiceService, public physicianService: PhysicianServiceService) {

  }

  addPatient = (data: any) => {
    if (data.valid) {
      this.patientService.save(data.value).subscribe((response: any) => alert(response.response));
    } else {
      alert("All Data is Required")
    }
  };

  physicianlist: any = []
  ngOnInit(): void {
    this.patientService.get().subscribe((response: any) => this.physicianlist = [...response])
    this.physicianService.get().subscribe((response: any) => this.physicianlist = [...response])
  }
}
