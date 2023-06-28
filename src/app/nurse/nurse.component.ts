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
    this.nurseService.save(data).subscribe((response: any) => console.log(response));
  };
}




