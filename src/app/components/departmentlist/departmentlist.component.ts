import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DepartmentServiceService } from 'src/app/service/Department/department-service.service';
import { Department } from './department';
@Component({
  selector: 'app-departmentlist',
  templateUrl: './departmentlist.component.html',
  styleUrls: ['./departmentlist.component.css'],
})
export class DepartmentlistComponent implements OnInit {
  Department: Department[] = [];
  allDepartment: Department[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  
  constructor(public departmentService: DepartmentServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.departmentService
      .get()
      .subscribe((response: any) => {
        this.Department = response;
        this.allDepartment = response;
      });

    }
    search(): void {
      if(this.searchQuery === '') {
      this.departmentService.get().subscribe((data) => {
        this.Department = this.allDepartment;
      });

    } else {
      this.Department = this.allDepartment.filter((dept) => {
        switch (this.searchAttribute) {
          case 'name':
            return dept.name.toLowerCase().includes(this.searchQuery.toLowerCase());
            case 'head':
              return dept.head === parseInt(this.searchQuery,10);
           case 'deptid':
            return dept.departmentId === parseInt(this.searchQuery, 7);
          default:
            return undefined;
        }
      });
    }
  }
}

//   departmentlist: any = [];
//   constructor(public departmentservice: DepartmentServiceService) {}
//   ngOnInit(): void {
//     this.departmentservice
//       .get()
//       .subscribe((response: any) => (this.departmentlist = [...response]));
//   }
// }
