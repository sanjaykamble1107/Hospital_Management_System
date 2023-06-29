import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { AppointmentServiceService } from '../service/Appointment/appointment-service.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent {

  appointmentForm = new FormGroup({
    appointmentId: new FormControl(''),
    patient: new FormControl(''),
    prepNurse: new FormControl(''),
    physician: new FormControl(''),
    startDateTime: new FormControl(''),
    endDateTime: new FormControl(''),
    examinationRoom: new FormControl('')

  })

  constructor(public appointmentservice: AppointmentServiceService) { }

  addAppointment = (data: any) => {
    if (data.Valid) {
      this.appointmentservice.save(data).subscribe((response: any) => console.log(response));
    } else {
      alert("All Data is Required")
    }
  }

}
