import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DepartmentServiceService } from 'src/app/service/Department/department-service.service';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent {
      departmentForm = new FormGroup({
      departmentId: new FormControl(),
      name: new FormControl(''),
      head: new FormControl('')
      
    })
  
    constructor(public departmentService: DepartmentServiceService) { }
    inputValue: string='';
    addDepartment = (data: any) => {
      if(data.valid){
        this.departmentService.save(data.value).subscribe((response: any) =>alert(response.response));
      }else{
        alert("All Data is Required")
      }
    }
  }
  

