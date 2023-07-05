import { Component } from '@angular/core';
import { ProcedureServiceService } from 'src/app/service/Procedure/procedure-service.service';

@Component({
  selector: 'app-procedurelist',
  templateUrl: './procedurelist.component.html',
  styleUrls: ['./procedurelist.component.css']
})
export class ProcedurelistComponent {
  Procedure: any=[]
  constructor(public procedureService: ProcedureServiceService){}
   ngOnInit(): void {
    this.procedureService
    .get()
    .subscribe((response:any)=>(this.Procedure=[...response]));
   }
}
