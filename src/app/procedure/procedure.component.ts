import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ProcedureServiceService } from '../service/Procedure/procedure-service.service';

@Component({
  selector: 'app-procedure',
  templateUrl: './procedure.component.html',
  styleUrls: ['./procedure.component.css']
})
export class ProcedureComponent {
  procedureForm = new FormGroup({
    code: new FormControl(),
    name: new FormControl(''),
    cost: new FormControl('') 
  })

  constructor(public procedureService: ProcedureServiceService) { }
  inputValue: string='';
  addProcedure = (data: any) => {
    if(data.valid){
      this.procedureService.save(data.value).subscribe((response: any) =>alert(response.response));
    }else{
      alert("All Data is Required")
    }
}
}