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
}
