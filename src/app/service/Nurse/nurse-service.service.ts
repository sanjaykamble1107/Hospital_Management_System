import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NurseServiceService {

  private rootUrl: string = "http://localhost:9090/api/nurse"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}`, data)
  }
  public get = () => {
    return this.http.get(`${this.rootUrl}`)
  }

  public getByID = (id: number) => {
    return this.http.get(`${this.rootUrl}/${id}`)
  }

  public updateRegisteredByEmpId(id: any, data: any) {
    return this.http.put(`${this.rootUrl}/registered/${id}`, data.registered)
  }

  public UpdateSsnByEmpid(id:any,data:any){
    return this.http.put(`${this.rootUrl}/ssn/${id}`, data.ssn)
  }
}
