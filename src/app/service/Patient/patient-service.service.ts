import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class PatientServiceService {
  private rootUrl: string = "http://localhost:9090/api/patient"

  constructor(private http: HttpClient, public token: TokenStorageService) { }

  private getHeaders(): HttpHeaders {
    const token = this.token.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
  public save = (data: any) => {
    const headers = this.getHeaders();
    return this.http.post(`${this.rootUrl}`, data, { headers })
  }
  public get = () => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}`, { headers })
  }

  public getById = (ssn: any) => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/SSN/${ssn}`, { headers })
  }

  public updateAddress = (ssn: any, data: any) => {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/address/${ssn}`, data, { headers })
  }
  public updatePhone = (ssn: any, data: any) => {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/phone/${ssn}`, data, { headers })
  }
}
