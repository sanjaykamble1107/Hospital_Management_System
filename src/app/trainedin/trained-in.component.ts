import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TrainedinServiceService } from '../service/TrainedIn/trainedin-service.service';

@Component({
  selector: 'app-trained-in',
  templateUrl: './trained-in.component.html',
  styleUrls: ['./trained-in.component.css']
})
export class TrainedInComponent {

  trainedInForm = new FormGroup({
    physician: new FormControl(),
    treatment: new FormControl(),
    certificationDate: new FormControl(),
    certificationExpires: new FormControl()  
  })

  constructor(public trainedinService: TrainedinServiceService) { }
  inputValue: string='';
  addTrainedIn = (data: any) => {
    if(data.valid){
      this.trainedinService.save(data.value).subscribe((response: any) =>alert(response.response));
    }else{
      alert("All Data is Required")
    }
  }
}
