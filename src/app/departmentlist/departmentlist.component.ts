import { Component, OnInit } from '@angular/core';
import { DepartmentServiceService } from '../service/Department/department-service.service';

@Component({
  selector: 'app-departmentlist',
  templateUrl: './departmentlist.component.html',
  styleUrls: ['./departmentlist.component.css'],
})
export class DepartmentlistComponent implements OnInit {
  departmentlist: any = [];
  constructor(public departmentservice: DepartmentServiceService) {}
  ngOnInit(): void {
    this.departmentservice
      .get()
      .subscribe((response: any) => (this.departmentlist = [...response]));
  }
}
