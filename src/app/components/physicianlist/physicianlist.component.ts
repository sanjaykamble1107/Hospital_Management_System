import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';

import { Physician } from './physician';

@Component({
  selector: 'app-physicianlist',
  templateUrl: './physicianlist.component.html',
  styleUrls: ['./physicianlist.component.css']
})
export class PhysicianlistComponent implements OnInit {
  Physician: Physician[] = [];
  allPhysician: Physician[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  appointmentService: any;
  constructor(public physicianService: PhysicianServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.physicianService
      .get()
      .subscribe((response: any) => {
        this.Physician = response;
        this.allPhysician = response;
      });


    }
    search(): void {
      if(this.searchQuery === '') {
      this.physicianService.get().subscribe((data) => {
        this.Physician = this.allPhysician;
      });

    } else {
      this.Physician = this.allPhysician.filter((phy) => {
        switch (this.searchAttribute) {
          case 'name':
            return phy.name.toLowerCase().includes(this.searchQuery.toLowerCase());
          case 'position':
            return phy.position.toLowerCase().includes(this.searchQuery.toLowerCase());
          case 'empid':
            return phy.employeeId === parseInt(this.searchQuery, 10);
          default:
            return undefined;
        }
      });
    }
  }
}


