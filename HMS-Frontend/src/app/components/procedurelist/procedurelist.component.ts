import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProcedureServiceService } from 'src/app/service/Procedure/procedure-service.service';
 import { Procedure } from './procedure';
@Component({
  selector: 'app-procedurelist',
  templateUrl: './procedurelist.component.html',
  styleUrls: ['./procedurelist.component.css']
})
export class ProcedurelistComponent  implements OnInit{
  Procedure: Procedure[] = [];
  allProcedure: Procedure[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  constructor(public ProcedureService: ProcedureServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.ProcedureService
      .get()
      .subscribe((response: any) => {
        this.Procedure= response;
        this.allProcedure = response;
    }  );
  }
      search(): void {
        if(this.searchQuery === '') {
        this.ProcedureService.get().subscribe((data) => {
          this.Procedure = this.allProcedure;
        });
  
      } else {
        this.Procedure = this.allProcedure.filter((prod) => {
          switch (this.searchAttribute) {
            case 'name':
              return prod.name.toLowerCase().includes(this.searchQuery.toLowerCase());
            
            case 'code':
              return prod.code === parseInt(this.searchQuery, 10);
            default:
              return undefined;
          }
        });
      }
    }
  }
//   Procedure: any=[]
//   constructor(public procedureService: ProcedureServiceService){}
//    ngOnInit(): void {
//     this.procedureService
//     .get()
//     .subscribe((response:any)=>(this.Procedure=[...response]));
//    }
// }
