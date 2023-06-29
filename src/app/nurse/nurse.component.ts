import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NurseServiceService } from '../service/Nurse/nurse-service.service';

@Component({
  selector: 'app-nurse',
  templateUrl: './nurse.component.html',
  styleUrls: ['./nurse.component.css']
})
export class NurseComponent {
  nurseForm = new FormGroup({
    employeeId: new FormControl(),
    name: new FormControl(''),
    position: new FormControl(''),
    registered: new FormControl(),
    ssn: new FormControl(),
  });


  constructor(public nurseService: NurseServiceService) { }
  addNurse = (data: any) => {
    if(data.valid){
      this.nurseService.save(data.value).subscribe((response: any) => alert(response.response));
    }
    else{
      alert("All Data is Required")
    }
    
  };
}




