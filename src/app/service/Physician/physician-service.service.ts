import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class PhysicianServiceService {
  private rootUrl: string = "http://localhost:9090/api/physician"

  constructor(private http: HttpClient, public authService: AuthService, public token: TokenStorageService) { }

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

  public getByEmpId = (employeeId: any) => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/${employeeId}`, { headers })
  }

  public updatePhysicianName(employeeId: any, data: any) {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/update/name/${employeeId}`, data, { headers })
  }

  public updatePhysicianPosition(employeeId: any, data: any) {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/update/position/${employeeId}`, data, { headers })
  }

  public updatePhysicianSSN(employeeId: any, data: any) {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/update/ssn/${employeeId}`, data, { headers })
  }

}
