import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PatientServiceService {
  private rootUrl: string = "http://localhost:9090/api/patient"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}`, data)
  }
  public get = () => {
    return this.http.get(`${this.rootUrl}`)
  }

  public getById = (ssn: any) => {
    return this.http.get(`${this.rootUrl}/SSN/${ssn}`)
  }

  public updateAddress = (ssn: any, data: any) => {
    return this.http.put(`${this.rootUrl}/address/${ssn}`, data)
  }
  public updatePhone = (ssn: any, data: any) => {
    return this.http.put(`${this.rootUrl}/phone/${ssn}`, data)
  }
}
