import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientServiceService } from 'src/app/service/Patient/patient-service.service';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';
@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css'],
})
export class PatientComponent implements OnInit {
  patientForm: any = new FormGroup({
    ssn: new FormControl(),
    name: new FormControl(''),
    address: new FormControl(''),
    phone: new FormControl(),
    insuranceID: new FormControl(),
    pcp: new FormControl(""),
  });

  constructor(public patientService: PatientServiceService, public physicianService: PhysicianServiceService, private router: ActivatedRoute, private route: Router) { }

  physicianlist: any = []
  isUpdate: boolean = false;

  isInputDisabledAddress: boolean = true;
  isInputDisabledPhone: boolean = true;
  isAddressEnable: boolean = false;
  isPhoneEnable: boolean = false;

  entityId = this.router.snapshot.params['ssn'];
  ngOnInit(): void {
    this.physicianService.get().subscribe((response: any) => this.physicianlist = [...response])

    if (this.entityId) {
      this.isUpdate = true;
      this.patientService.getById(this.entityId).subscribe((response: any) => this.patientForm = new FormGroup({
        ssn: new FormControl({ value: response['ssn'], disabled: true }),
        name: new FormControl({ value: response['name'], disabled: true }),
        address: new FormControl({ value: response['address'], disabled: true }),
        phone: new FormControl({ value: response['phone'], disabled: true }),
        insuranceID: new FormControl({ value: response['insuranceID'], disabled: true }),
        pcp: new FormControl({ value: response['pcp'], disabled: true }),
      }))
    } else {
      this.isUpdate = false;
    }
  }

  submitPatient = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (this.isAddressEnable) {
          this.enableAll();
          this.patientService.updateAddress(this.entityId, data.value).subscribe((result) => {
            alert("Address Updated!")
            this.route.navigate(["/PatientList"])
          })
        }

        if (this.isPhoneEnable) {
          this.enableAll();
          this.patientService.updatePhone(this.entityId, data.value).subscribe((result) => {
            alert("Phone Number Updated!")
            this.route.navigate(["/PatientList"])
          })
        }

      } else {
        this.patientService.save(data.value).subscribe((response: any) => {
          alert(response.response)
          this.route.navigate(["/PatientList"])
        });

      }
    } else {
      alert("All Data is Required")
    }
  };

  enableAddress() {
    this.patientForm.get('address').enable();
    this.isInputDisabledPhone = false;
    this.isAddressEnable = true;
  }

  enablePhone() {
    this.patientForm.get('phone').enable();
    this.isInputDisabledAddress = false;
    this.isPhoneEnable = true;
  }


  enableAll() {
    this.patientForm.get('phone').enable();
    this.patientForm.get('address').enable();
    this.patientForm.get('ssn').enable();
    this.patientForm.get('name').enable();
    this.patientForm.get('pcp').enable();
    this.patientForm.get('insuranceID').enable();
  }
}