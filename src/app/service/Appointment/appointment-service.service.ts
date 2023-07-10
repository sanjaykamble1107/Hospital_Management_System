import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppointmentServiceService {
 

  private rootUrl: string = "http://localhost:9090/api/appointment"
  

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}/`, data)
  }

  public get =() =>{
    return this.http.get(`${this.rootUrl}`)
  } 
  public getByAppId = (appointmentId: any) => {
    return this.http.get(`${this.rootUrl}/${appointmentId}`)
  }
  public updateAppointmentExaminationRoom(appointmentId: any, data: any) {
    return this.http.put(`${this.rootUrl}/update/examinationRoom/${appointmentId}`, data)
  }
}
