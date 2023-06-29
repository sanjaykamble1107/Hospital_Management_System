import { Component, OnInit } from '@angular/core';
import { NurseServiceService } from '../service/Nurse/nurse-service.service';

@Component({
  selector: 'app-nursetable',
  templateUrl: './nursetable.component.html',
  styleUrls: ['./nursetable.component.css']
})
export class NursetableComponent implements OnInit {

  nurselist: any =[]
  constructor(public nurseservice: NurseServiceService){}
  ngOnInit(): void {
    this.nurseservice.get().subscribe((response:any)=>this.nurselist=[...response])
  }

    
      
    

}
