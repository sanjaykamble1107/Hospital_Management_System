import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentServiceService } from 'src/app/service/Department/department-service.service';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.css']
})
export class DepartmentComponent {
  departmentForm: any = new FormGroup({
    name: new FormControl(''),
    head: new FormControl("")
  })

  constructor(public departmentService: DepartmentServiceService, private router: ActivatedRoute, private route: Router, public physicianService: PhysicianServiceService) { }
  inputValue: string = '';

  isUpdate: boolean = false;

  isInputDisabledName: boolean = true;
  isInputDisabledHead: boolean = true;
  isNameEnable: boolean = false;
  isHeadEnable: boolean = false;

  physicianlist: any = []

  entityId = this.router.snapshot.params['deptId'];
  ngOnInit(): void {
    this.physicianService.get().subscribe((response: any) => this.physicianlist = [...response])
    if (this.entityId) {
      this.isUpdate = true;
      this.departmentService.getById(this.entityId).subscribe((response: any) => this.departmentForm = new FormGroup({
        name: new FormControl({ value: response['name'], disabled: true }),
        head: new FormControl({ value: response['head'], disabled: true })
      }))
    } else {
      this.isUpdate = false;
    }
  }

  submitDepartment = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (this.isNameEnable) {
          this.enableAll();
          this.departmentService.updateDepartmentName(this.entityId, data.value).subscribe((result) => {
            alert("Name Updated!")
            this.route.navigate(["/DepartmentList"])
          })
        }

        if (this.isHeadEnable) {
          this.enableAll();
          this.departmentService.updateHead(this.entityId, data.value).subscribe((result) => {
            alert("Head Updated!")
            this.route.navigate(["/DepartmentList"])
          })
        }
      } else {
        this.departmentService.save(data.value).subscribe((response: any) => {
          alert(response.response)
          this.route.navigate(["/DepartmentList"])
        });

      }
    } else {
      alert("All Data is Required")
    }
  };

  enableName() {
    this.departmentForm.get('name').enable();
    this.isInputDisabledHead = false;
    this.isNameEnable = true;
  }

  enableHead() {
    this.departmentForm.get('head').enable();
    this.isInputDisabledName = false;
    this.isHeadEnable = true;
  }

  enableAll() {
    this.departmentForm.get('name').enable();
    this.departmentForm.get('head').enable();
  }
}


