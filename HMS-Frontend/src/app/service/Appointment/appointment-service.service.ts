import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AppointmentServiceService {
  private rootUrl: string = 'http://localhost:9090/api/appointment';

  constructor(private http: HttpClient, public token: TokenStorageService) {}

  private getHeaders(): HttpHeaders {
    const token = this.token.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
  public save = (data: any) => {
    const headers = this.getHeaders();
    return this.http.post(`${this.rootUrl}/`, data, { headers });
  };

  public get = () => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}`, { headers });
  };
  public getByAppId = (appointmentId: any) => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/appointmentId/${appointmentId}`, { headers });
  };
  public updateExaminationRoom(appointmentId: any, data: any) {
    const headers = this.getHeaders();
     return this.http.put(`${this.rootUrl}/room/${appointmentId}`, data,{ headers }
    );
  }
}
