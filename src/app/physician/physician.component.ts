import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-physician',
  templateUrl: './physician.component.html',
  styleUrls: ['./physician.component.css']
})
export class PhysicianComponent {
  physicianForm = new FormGroup({
    employeeId: new FormControl(),
    name: new FormControl(''),
    position: new FormControl(''),
    ssn: new FormControl()
  })

  addPhysician=(formData:any)=>{
    console.log(formData);
  }
}
