import { Component, OnInit } from '@angular/core';
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



  constructor(public nurseService: NurseServiceService, private router: ActivatedRoute) {
  }
  ngOnInit(): void {
    this.nurseService.getByID(this.router.snapshot.params['id']).subscribe((result: any) => {
      this.nurseForm = new FormGroup({
        employeeId: new FormControl(result['employeeId']),
        name: new FormControl(result['name']),
        position: new FormControl(result['position']),
        registered: new FormControl(result[' registered']),
        ssn: new FormControl(result['ssn'])
      })
    })

  }
  addNurse = (data: any) => {
  
    if (data.valid) {
      this.nurseService.save(data.value).subscribe((response: any) => alert(response.response));
    }
    else {
      alert("All Data is Required")
    }

  };

  updateRegisteredByEmpId = () => {
    console.log(this.nurseForm);

    this.nurseService.updateRegisteredByEmpId(this.router.snapshot.params['id'], this.nurseForm.value)
      .subscribe((result) => {
        console.log(result);
      })
  }

  UpdateSsnByEmpid =()=>{
    this.nurseService.UpdateSsnByEmpid(this.router.snapshot.params['id'],this.nurseForm.value)
    .subscribe((result:any)=>{
      console.log(result,"record added successfully")
    })
  }
}
