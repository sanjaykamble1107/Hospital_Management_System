import { Component ,OnInit} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { PhysicianServiceService } from '../service/Physician/physician-service.service';


@Component({
  selector: 'app-updatephysician',
  templateUrl: './updatephysician.component.html',
  styleUrls: ['./updatephysician.component.css']
})
export class UpdatephysicianComponent implements OnInit{
  alert:boolean=false;
  editphysician=new FormGroup({
    employeeId:new FormControl(''),
    name:new FormControl(''),
    position:new FormControl(''),
    ssn:new FormControl('')
  })
  ngOnInit(): void {
    this.phy.getCurrentData(this.router.snapshot.params['employeeId']).subscribe((result:any)=>{
      this.editphysician=new FormGroup({
        employeeId:new FormControl(result['employeeId']),
        name:new FormControl(result['name']),
        position:new FormControl(result['position']),
        ssn:new FormControl(result['ssn'])
      })
    })
  }

  closealert(){
    this.alert=false;
  }

  updatephysician() {
    this.phy.updatephysicianname(this.router.snapshot.params['employeeId'], this.editphysician.value)
      .subscribe((result: any) => {
        const specificProperty = result.name;
        console.log(specificProperty, "data added successfully!!");
        this.alert=true;
      });
  }
  

  constructor(private phy:PhysicianServiceService,private router:ActivatedRoute){
  }
  }