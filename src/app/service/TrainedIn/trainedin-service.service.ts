import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrainedinServiceService {
  private rootUrl: string = "http://localhost:9090/api/trained_in"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}`, data)
  }
   public  get =() =>{
    return this.http.get(`${this.rootUrl}`)
   }
  
}
