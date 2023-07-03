import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { NurseServiceService } from '../service/Nurse/nurse-service.service';


@Component({
  selector: 'app-nursetable',
  templateUrl: './nursetable.component.html',
  styleUrls: ['./nursetable.component.css']
})
export class NursetableComponent implements OnInit {

  nurselist: any = []
  nurse: any = [];
  searchVal: any = 0;
  selectedVal: number = 0;
  

  constructor(public nurseservice: NurseServiceService ) { }

  ngOnInit(): void {
    this.nurseservice
      .get()
      .subscribe((response: any) => (this.nurselist = [...response])); {
    }
    

  }

  getSelectVal = (event: any) => {
    this.selectedVal = event.target.value;
  }

  search = () => {
    if (this.selectedVal == 1) {
      this.nurselist.splice(0,this.nurselist.length)
      this.nurseservice.getByID(this.searchVal).subscribe((response: any) => (this.nurselist.push(response)));
    }
    else{
      this.ngOnInit();
    }

  }
  

}
