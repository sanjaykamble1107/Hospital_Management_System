import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AffiliatedwithServiceService {
  private rootUrl: string = "http://localhost:9090/api/affiliated_with"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}/post`, data)
  }
   public  get =() =>{
    return this.http.get(`${this.rootUrl}`)
   }


}
