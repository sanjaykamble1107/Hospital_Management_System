import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentServiceService } from 'src/app/service/Appointment/appointment-service.service';
import { NurseServiceService } from 'src/app/service/Nurse/nurse-service.service';
import { PhysicianServiceService } from 'src/app/service/Physician/physician-service.service';
import { RoomServiceService } from 'src/app/service/Room/room-service.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
})
export class AppointmentComponent {
  appointmentForm: any = new FormGroup({
    patient: new FormControl(),
    prepNurse: new FormControl(""),
    physician: new FormControl(""),
    startDateTime: new FormControl(),
    endDateTime: new FormControl(),
    examinationRoom: new FormControl(""),
  });

  Roomlist!: any[];
  nurselist: any = [];
  physicianlist: any = [];
  isUpdate: boolean = false;
  isInputDisabledExaminationRoom: boolean = true;
  isExaminationRoomEnable: boolean = false;
  entityId = this.router.snapshot.params['appointmentId'];
  minDateTime: string;

  constructor(
    public appointmentservice: AppointmentServiceService,
    public physicianService: PhysicianServiceService,
    public nurseService: NurseServiceService,
    public roomService: RoomServiceService,
    private router: ActivatedRoute,
    private route: Router
  ) {
    const now = new Date();
    this.minDateTime = now.toISOString().slice(0, 16);
  }
  ngOnInit(): void {
    this.physicianService
      .get()
      .subscribe((response: any) => (this.physicianlist = [...response]));

    this.nurseService
      .get()
      .subscribe((response: any) => (this.nurselist = [...response]));
    this.roomService
      .get()
      .subscribe((response: any) => (this.Roomlist = [...response]));
    if (this.entityId) {
      this.isUpdate = true;

      this.appointmentservice.getByAppId(this.entityId).subscribe(
        (response: any) => {
          (this.appointmentForm = new FormGroup({
            patient: new FormControl({
              value: response['patient'],
              disabled: true,
            }),
            prepNurse: new FormControl({
              value: response['prepNurse'],
              disabled: true,
            }),
            physician: new FormControl({
              value: response['physician'],
              disabled: true,
            }),
            startDateTime: new FormControl({
              value: response['startDateTime'],
              disabled: true,
            }),
            endDateTime: new FormControl({
              value: response['endDateTime'],
              disabled: true,
            }),
            examinationRoom: new FormControl({
              value: response['examinationRoom'],
              disabled: true,
            }),
          }))
        }
      );
    } else {
      this.isUpdate = false;
    }
  }
  submitAppointment = (data: any) => {
    if (data.valid) {
      if (this.isUpdate) {
        if (this.isExaminationRoomEnable) {
          this.enableAll();
          this.appointmentservice
            .updateExaminationRoom(this.entityId, data.value)
            .subscribe((result) => {
              alert('ExaminationRoom Updated!');
              this.route.navigate(['/AppointmentList']);
            });
        }
      } else {
        this.appointmentservice.save(data.value).subscribe((response: any) => {
          alert(response.response);
          this.route.navigate(['/AppointmentList']);
        });
      }
    } else {
      alert('All Data is Required');
    }
  };
  enableExaminationRoom() {
    this.appointmentForm.get('examinationRoom').enable();

    this.isExaminationRoomEnable = true;
  }
  enableAll() {
    this.appointmentForm.get('patient').enable();
    this.appointmentForm.get('prepNurse').enable();
    this.appointmentForm.get('physician').enable();
    this.appointmentForm.get('startDateTime').enable();
    this.appointmentForm.get('endDateTime').enable();
    this.appointmentForm.get('examinationRoom').enable();
  }
}
