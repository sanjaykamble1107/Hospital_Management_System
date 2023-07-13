import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AffiliatedwithServiceService {
  private rootUrl: string = "http://localhost:9090/api/affiliated_with"

  constructor(private http: HttpClient, public token: TokenStorageService) { }

  private getHeaders(): HttpHeaders {
    const token = this.token.getToken();
    return new HttpHeaders().set('Authorization', `Bearer ${token}`);
  }
  public save = (data: any) => {
    const headers = this.getHeaders();
    return this.http.post(`${this.rootUrl}/post`, data, { headers })
  }
  public get = () => {
    const headers = this.getHeaders();
    return this.http.get(`${this.rootUrl}`, { headers })
  }
}
