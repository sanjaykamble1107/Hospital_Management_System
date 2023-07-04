
import { Component, OnInit } from '@angular/core';
import { AppointmentServiceService } from 'src/app/service/Appointment/appointment-service.service';

@Component({
  selector: 'app-appointmentlist',
  templateUrl: './appointmentlist.component.html',
  styleUrls: ['./appointmentlist.component.css']
})

export class AppointmentlistComponent implements OnInit {
  

  appointmentlist: any = []
  constructor(public appointmentservice: AppointmentServiceService) { }
  ngOnInit(): void {
    this.appointmentservice.get().subscribe((response: any) => this.appointmentlist = [...response])

  }
}
