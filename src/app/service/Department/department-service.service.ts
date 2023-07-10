import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class DepartmentServiceService {
  private rootUrl: string = 'http://localhost:9090/api/department';

  constructor(private http: HttpClient, public token: TokenStorageService) {}

  private getHeaders(): HttpHeaders {
    const token = this.token.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
  public save = (data: any) => {
    const headers = this.getHeaders();
    return this.http.post(`${this.rootUrl}`, data, { headers });
  };
  public get = () => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/`, { headers });
  };

  public getById = (deptId: any) => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/${deptId}`, { headers });
  };

  public updateHead(deptId: any, data: any) {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/update/headid/${deptId}`, data, {
      headers,
    });
  }

  public updateDepartmentName(deptId: any, data: any) {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/update/deptname/${deptId}`, data, {
      headers,
    });
  }
}
