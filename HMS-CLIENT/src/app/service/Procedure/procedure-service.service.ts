import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProcedureServiceService {
  private rootUrl: string = "http://localhost:9090/api/procedure"

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

  public getById = (code: number) => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}/${code}`, { headers })
  }

  public updateName = (data: any, code: number) => {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/name/${code}`, data, { headers })
  }

  public updateCost = (data: any, code: number) => {
    const headers = this.getHeaders();
    return this.http.put(`${this.rootUrl}/cost/${code}`, data, { headers })
  }

}
