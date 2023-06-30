import { Component } from '@angular/core';
import { FormGroup, FormControl,Validators } from '@angular/forms';
import { PhysicianServiceService } from '../service/Physician/physician-service.service';

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

  constructor(public physicianService: PhysicianServiceService) { }
  inputValue: string='';
  addPhysician = (data: any) => {
    if(data.valid){
      this.physicianService.save(data.value).subscribe((response: any) =>alert(response.response));
    }else{
      alert("All Data is Required")
    }
  }
}
