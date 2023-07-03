import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AppointmentServiceService } from '../service/Appointment/appointment-service.service';
import { NurseServiceService } from '../service/Nurse/nurse-service.service';
import { PhysicianServiceService } from '../service/Physician/physician-service.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent implements OnInit {

  appointmentForm = new FormGroup({
    appointmentId: new FormControl(''),
    patient: new FormControl(''),
    prepNurse: new FormControl(''),
    physician: new FormControl(''),
    startDateTime: new FormControl(''),
    endDateTime: new FormControl(''),
    examinationRoom: new FormControl('')
  })



  constructor(public appointmentservice: AppointmentServiceService, public physicianService: PhysicianServiceService,public nurseService:NurseServiceService) {

  }
  nurselist: any = []
  physicianlist: any = []
  ngOnInit(): void {

    this.physicianService.get().subscribe((response: any) => this.physicianlist = [...response])

    this.nurseService.get().subscribe((response: any)=> this.nurselist=[...response])

  }

  public character: string | undefined;
  handleroom(event: any) {
    this.character = event.target.value;

  }

  addAppointment = (data: any) => {
    if (data.valid) {
      data.value.examinationRoom = data.value.examinationRoom.toUpperCase();
      this.appointmentservice.save(data.value).subscribe((response: any) => alert(response.response));
    } else {
      alert("All Data is Required")
    }
  }

}
