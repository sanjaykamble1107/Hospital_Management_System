import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AffiliatedwithServiceService } from 'src/app/service/Affiliatedwith/affiliatedwith-service.service';
import { DepartmentServiceService } from 'src/app/service/Department/department-service.service';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';


@Component({
  selector: 'app-affiliatedwith',
  templateUrl: './affiliatedwith.component.html',
  styleUrls: ['./affiliatedwith.component.css'],
})
export class AffiliatedwithComponent {
  affiliatedWithForm = new FormGroup({
    physician: new FormControl(),
    department: new FormControl(),
    primaryAffiliation: new FormControl(),
  });

  constructor(
    public affiliatedwithService: AffiliatedwithServiceService,
    public departmentService: DepartmentServiceService,
    public physicianService: PhysicianServiceService
  ) {}
  departmentlist: any = [];
  physicianlist: any = [];
  ngOnInit(): void {
    this.physicianService
      .get()
      .subscribe((response: any) => (this.physicianlist = [...response]));

    this.departmentService
      .get()
      .subscribe((response: any) => (this.departmentlist = [...response]));
  }
  
  addAffiliatedWith = (data: any) => {
    console.log(data.value)
    if (data.valid) {
      this.affiliatedwithService
        .save(data.value)
        .subscribe((response: any) => alert(response.response));
    } else {
      alert('All Data is Required');
    }
  };
}
