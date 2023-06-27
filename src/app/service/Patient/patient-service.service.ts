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
}
