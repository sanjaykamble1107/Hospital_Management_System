import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientServiceService } from 'src/app/service/Patient/patient-service.service';
import{Patient} from './patient';
@Component({
  selector: 'app-patienttable',
  templateUrl: './patienttable.component.html',
  styleUrls: ['./patienttable.component.css'],
})
export class PatienttableComponent implements OnInit {

  Patient: Patient[] = [];
  allPatient: Patient[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  constructor(public PatientService: PatientServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.PatientService
      .get()
      .subscribe((response: any) => {
        this.Patient= response;
        this.allPatient = response;
    }  );
  }
      search(): void {
        if(this.searchQuery === '') {
        this.PatientService.get().subscribe((data) => {
          this.Patient= this.allPatient;
        });
  
      } else {
        this.Patient = this.allPatient.filter((pat) => {          
          switch (this.searchAttribute) {
           case 'insuranceId':
                 return pat.insuranceID === parseInt(this.searchQuery,10);
           case 'name':
                 return pat.name.toLowerCase().includes(this.searchQuery.toLowerCase());
           case 'ssn':              
                 return pat.ssn === parseInt(this.searchQuery, 10);
           case 'pcp':
                 return pat.pcp === parseInt(this.searchQuery,10);
          default:              
                 return undefined;
          }
        });
      }
    }
  }
//   patientlist: any = [];
//   constructor(public patientservice: PatientServiceService) {}
//   ngOnInit(): void {
//     this.patientservice
//       .get()
//       .subscribe((response: any) => (this.patientlist = [...response]));
//   }
// }
