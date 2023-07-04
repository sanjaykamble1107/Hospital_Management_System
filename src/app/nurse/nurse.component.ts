import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NurseServiceService } from '../service/Nurse/nurse-service.service';

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.css']
})
export class NurseComponent implements OnInit {
  nurseForm: any = new FormGroup({
    employeeId: new FormControl(),
    name: new FormControl(''),
    position: new FormControl(''),
    registered: new FormControl(),
    ssn: new FormControl(),
  });

  isUpdate: boolean = false;
  @ViewChild('registerationSelect')
  myInput!: ElementRef;

  isInputDisabledRegistered: boolean = true;
  isInputDisabledSSN: boolean = true;


  constructor(public nurseService: NurseServiceService, private router: ActivatedRoute) {
  }
  ngOnInit(): void {


    const entityId = this.router.snapshot.params['id'];
    if (entityId) {
      this.isUpdate = true;
      this.nurseService.getByID(this.router.snapshot.params['id']).subscribe((result: any) => {
        this.nurseForm = new FormGroup({
          employeeId: new FormControl({ value: result['employeeId'], disabled: true }),
          name: new FormControl({ value: result['name'], disabled: true }),
          position: new FormControl({ value: result['position'], disabled: true }),
          registered: new FormControl({ value: result['registered'], disabled: true }),
          ssn: new FormControl({ value: result['ssn'], disabled: true })
        })
      })
    } else {
      this.isUpdate = false;
    }

  }

  submit = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (!this.isInputDisabledRegistered) {
          this.nurseService.updateRegisteredByEmpId(this.router.snapshot.params['id'], this.nurseForm.value)
            .subscribe((result) => {
              console.log(result);
            })
        }
        if (!this.isInputDisabledSSN) {
          this.nurseService.UpdateSsnByEmpid(this.router.snapshot.params['id'], this.nurseForm.value)
            .subscribe((result: any) => {
              console.log(result)
            })
        }
      } else {
        this.nurseService.save(data.value).subscribe((response: any) => alert(response.response));
      }
    }
    else {
      alert("All Data is Required")
    }
  };

  enableRegistered() {
    this.nurseForm.get('registered').enable();
    this.isInputDisabledRegistered = false
  }

  enableSSN() {
    this.nurseForm.get('ssn').enable();
    this.isInputDisabledSSN = false
  }
}
