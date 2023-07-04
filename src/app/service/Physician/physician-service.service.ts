import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PhysicianServiceService {
  private rootUrl: string = "http://localhost:9090/api/physician"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}`, data)
  }

  public get = () => {
    return this.http.get(`${this.rootUrl}`)
  }

  public getByEmpId = (employeeId: any) => {
    return this.http.get(`${this.rootUrl}/${employeeId}`)
  }
  
  public updatePhysicianName(employeeId: any, data: any) {
    return this.http.put(`${this.rootUrl}/update/name/${employeeId}`, data)
  }

  public updatePhysicianPosition(employeeId: any, data: any) {
    return this.http.put(`${this.rootUrl}/update/position/${employeeId}`, data)
  }

  public updatePhysicianSSN(employeeId: any, data: any) {
    return this.http.put(`${this.rootUrl}/update/ssn/${employeeId}`, data)
  }

}
