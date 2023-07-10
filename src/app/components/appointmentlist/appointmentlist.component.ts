
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppointmentServiceService } from 'src/app/service/Appointment/appointment-service.service';
import { Appointment } from './appointment';

@Component({

  selector: 'app-appointmentlist',
  templateUrl: './appointmentlist.component.html',
  styleUrls: ['./appointmentlist.component.css']
})

export class AppointmentlistComponent implements OnInit {
  Appointment: Appointment[] = [];
  allAppointment: Appointment[] = [];
  searchAttribute: string = '';
  searchQuery: string = '';
  constructor(public AppointmentService: AppointmentServiceService, private router: ActivatedRoute) { }
  ngOnInit() {
    this.AppointmentService
      .get()
      .subscribe((response: any) => {
        this.Appointment= response;
        this.allAppointment = response;
    }  );
  }
      search(): void {
        if(this.searchQuery === '') {
        this.AppointmentService.get().subscribe((data) => {
          this.Appointment = this.allAppointment;
        });
  
      } else {
        this.Appointment = this.allAppointment.filter((app) => {
          switch (this.searchAttribute) {
            case 'prepNurse':
              return app.prepNurse === parseInt(this.searchQuery,10);
            case 'phyid':
              return app.physician === parseInt(this.searchQuery,10);  
            case 'patid':
              return app.patient === parseInt(this.searchQuery,10);
            case 'appid':
              return app.appointmentId === parseInt(this.searchQuery, 10);
            default:
              return undefined;
          }
        });
      }
    }
  }
  





  