import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';
import { ProcedureServiceService } from 'src/app/service/Procedure/procedure-service.service';
import { TrainedinServiceService } from 'src/app/service/TrainedIn/trainedin-service.service';

@Component({
  selector: 'app-trained-in',
  templateUrl: './trained-in.component.html',
  styleUrls: ['./trained-in.component.css']
})
export class TrainedInComponent implements OnInit {

  trainedInForm = new FormGroup({
    physician: new FormControl(""),
    treatment: new FormControl(""),
    certificationDate: new FormControl(),
    certificationExpires: new FormControl()
  })

  physicianList: any;
  procedureList: any;
  constructor(public trainedinService: TrainedinServiceService, public physicianService: PhysicianServiceService, public procedureService: ProcedureServiceService) { }
  ngOnInit(): void {
    this.physicianService.get().subscribe((response: any) => this.physicianList = [...response]);
    this.procedureService.get().subscribe((response: any) => this.procedureList = [...response]);
  }
  inputValue: string = '';
  addTrainedIn = (data: any) => {
    if (data.valid) {
      this.trainedinService.save(data.value).subscribe((response: any) => alert(response.response));
    } else {
      alert("All Data is Required")
    }
  }
}
