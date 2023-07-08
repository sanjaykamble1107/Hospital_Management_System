import { HttpClient } from '@angular/common/http';
import { ReturnStatement } from '@angular/compiler';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProcedureServiceService {
  private rootUrl: string = "http://localhost:9090/api/procedure"

  constructor(private http: HttpClient) { }

  public save = (data: any) => {
    return this.http.post(`${this.rootUrl}`, data)
  }

  public get = () => {
    return this.http.get(`${this.rootUrl}`)
  }

  public getById = (code: number) => {
    return this.http.get(`${this.rootUrl}/${code}`)
  }

  public updateName = (data: any, code: number) => {
    return this.http.put(`${this.rootUrl}/name/${code}`, data)
  }
  
  public updateCost = (data: any, code: number) => {
    return this.http.put(`${this.rootUrl}/cost/${code}`, data)
  }

}
