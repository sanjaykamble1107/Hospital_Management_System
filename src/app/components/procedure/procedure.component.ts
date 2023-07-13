import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProcedureServiceService } from 'src/app/service/Procedure/procedure-service.service';

@Component({
  selector: 'app-procedure',
  templateUrl: './procedure.component.html',
  styleUrls: ['./procedure.component.css']
})
export class ProcedureComponent implements OnInit {
  procedureForm: any = new FormGroup({
    name: new FormControl(''),
    cost: new FormControl('')
  })

  constructor(public procedureService: ProcedureServiceService, private router: ActivatedRoute, private route: Router) { }
  entityId = this.router.snapshot.params['code'];
  isUpdate: boolean = false;

  isInputDisabledName: boolean = true;
  isInputDisabledCost: boolean = true;
  isNameEnable: boolean = false;
  isCostEnable: boolean = false;

  ngOnInit(): void {
    if (this.entityId) {
      this.isUpdate = true;
      this.procedureService.getById(this.entityId).subscribe((result: any) => this.procedureForm = new FormGroup({
        name: new FormControl({ value: result['name'], disabled: true }),
        cost: new FormControl({ value: result['cost'], disabled: true })
      }))
    } else {
      this.isUpdate = false;
    }

  }
  inputValue: string = '';
  submitData = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (this.isCostEnable) {
          this.enableAll();
          this.procedureService.updateCost(data.value, this.entityId).subscribe((response: any) => {
            alert("Cost updated Successfully!");
            this.route.navigate(["/ProcedureList"])
          })
        }

        if (this.isNameEnable) {
          this.enableAll();
          this.procedureService.updateName(data.value, this.entityId).subscribe((response: any) => {
            alert("Name updated Successfully!");
            this.route.navigate(["/ProcedureList"])
          })
        }
      } else {
        this.procedureService.save(data.value).subscribe((response: any) => alert(response.response));
        this.route.navigate(["/ProcedureList"])
      }
    } else {
      alert("All Data is Required")
    }
  }

  enableName() {
    this.procedureForm.get('name').enable();
    this.isInputDisabledCost = false;
    this.isNameEnable = true;
  }

  enableCost() {
    this.procedureForm.get('cost').enable();
    this.isInputDisabledName = false;
    this.isCostEnable = true;
  }

  enableAll() {
    this.procedureForm.get('cost').enable();
    this.procedureForm.get('name').enable();
  }
}