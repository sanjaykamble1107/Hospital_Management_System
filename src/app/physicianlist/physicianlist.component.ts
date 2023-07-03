import { Component, OnInit } from '@angular/core';

import { PhysicianServiceService } from '../service/Physician/physician-service.service';

@Component({
  selector: 'app-physicianlist',
  templateUrl: './physicianlist.component.html',
  styleUrls: ['./physicianlist.component.css']
})
export class PhysicianlistComponent implements OnInit {
  
  Physician: any=[]
 constructor(public physicianService: PhysicianServiceService){}
  ngOnInit(): void {
   this.physicianService
   .get()
   .subscribe((response:any)=>(this.Physician=[...response]));
  }

 
      }
   

