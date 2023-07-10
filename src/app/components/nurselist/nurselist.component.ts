import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NurseServiceService } from 'src/app/service/Nurse/nurse-service.service'

import{Nurse} from './nurse'

@Component({
  selector: 'app-nurselist',
  templateUrl: './nurselist.component.html',
  styleUrls: ['./nurselist.component.css']
})
export class NursetableComponent implements OnInit {
  Nurse: Nurse[] = [];
  allNurse: Nurse[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  
  constructor(public nurseService: NurseServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.nurseService
      .get()
      .subscribe((response: any) => {
        this.Nurse = response;
        this.allNurse= response;
      });

    }
    search(): void {
      if(this.searchQuery === '') {
      this.nurseService.get().subscribe((data) => {
        this.Nurse= this.allNurse;
      });

    } else {
      this.Nurse = this.allNurse.filter((nur) => {
        switch (this.searchAttribute) {
          case 'name':
            return nur.name.toLowerCase().includes(this.searchQuery.toLowerCase());
            case 'ssn':
              return nur.ssn === parseInt(this.searchQuery,10);
           case 'empid':
            return nur.employeeId === parseInt(this.searchQuery, 10);
          default:
            return undefined;
        }
      });
    }
  }
}

//   nurselist: any = []
//   nurse: any = [];
//   searchVal: any = 0;
//   selectedVal: number = 0;
  

//   constructor(public nurseservice: NurseServiceService ) { }

//   ngOnInit(): void {
//     this.nurseservice
//       .get()
//       .subscribe((response: any) => (this.nurselist = [...response])); {
//     }
    

//   }

//   getSelectVal = (event: any) => {
//     this.selectedVal = event.target.value;
//   }

//   search = () => {
//     if (this.selectedVal == 1) {
//       this.nurselist.splice(0,this.nurselist.length)
//       this.nurseservice.getByID(this.searchVal).subscribe((response: any) => (this.nurselist.push(response)));
//     }
//     else{
//       this.ngOnInit();
//     }

//   }
  

// }
