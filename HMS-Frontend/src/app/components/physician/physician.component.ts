import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';



@Component({
  selector: 'app-physician',
  templateUrl: './physician.component.html',
  styleUrls: ['./physician.component.css']
})
export class PhysicianComponent {
  physicianForm: any = new FormGroup({
    name: new FormControl(''),
    position: new FormControl(''),
    ssn: new FormControl()
  })
  isUpdate: boolean = false;

  isInputDisabledName: boolean = true;
  isInputDisabledPosition: boolean = true;
  isInputDisabledSSN: boolean = true;

  isNameEnable: boolean = false;
  isPostionEnable: boolean = false;
  isSSNEnable: boolean = false;

  constructor(public physicianService: PhysicianServiceService, private router: ActivatedRoute, private route: Router) { }
  ngOnInit(): void {
    const entityId = this.router.snapshot.params['employeeId'];
    if (entityId) {
      this.isUpdate = true;
      this.physicianService.getByEmpId(this.router.snapshot.params['employeeId']).subscribe((result: any) => {
        this.physicianForm = new FormGroup({
          name: new FormControl({ value: result['name'], disabled: true }),
          position: new FormControl({ value: result['position'], disabled: true }),
          ssn: new FormControl({ value: result['ssn'], disabled: true })
        })
      })
    } else {
      this.isUpdate = false;
    }
  }

  inputValue: string = '';
  submitPhysician = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (this.isNameEnable) {
          this.enableAll();
          this.physicianService.updatePhysicianName(this.router.snapshot.params['employeeId'], data.value)
            .subscribe((result: any) => {
              alert("Name Added Successfully!")
              this.route.navigate(["/PhysicianList"])
            });
        }
        if (this.isPostionEnable) {
          this.enableAll();
          this.physicianService.updatePhysicianPosition(this.router.snapshot.params['employeeId'], data.value)
            .subscribe((result: any) => {
              alert("Position Added Successfully!")
              this.route.navigate(["/PhysicianList"])
            });
        }
        if (this.isSSNEnable) {
          this.enableAll();
          this.physicianService.updatePhysicianSSN(this.router.snapshot.params['employeeId'], data.value)
            .subscribe((result: any) => {
              alert("SSN Added Successfully!")
              this.route.navigate(["/PhysicianList"])
            });
        }
      } else {
        this.physicianService.save(data.value).subscribe((response: any) => alert(response.response));
        this.route.navigate(["/PhysicianList"])
      }
    } else {
      alert("All Data is Required")
    }
  }
  enableName() {
    this.physicianForm.get('name').enable();
    this.isInputDisabledPosition = false
    this.isInputDisabledSSN = false
    this.isNameEnable = true
  }

  enablePosition() {
    this.physicianForm.get('position').enable();
    this.isInputDisabledName = false
    this.isInputDisabledSSN = false
    this.isPostionEnable = true
  }
  enableSSN() {
    this.physicianForm.get('ssn').enable();
    this.isInputDisabledPosition = false
    this.isInputDisabledName = false
    this.isSSNEnable = true
  }

  enableAll() {
    this.physicianForm.get('ssn').enable();
    this.physicianForm.get('position').enable();
    this.physicianForm.get('name').enable();
  }

  alphaOnly(e: any) {  
    var regex = new RegExp("^[a-zA-Z]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return true;
    }

    e.preventDefault();
    return false;
  }

}

