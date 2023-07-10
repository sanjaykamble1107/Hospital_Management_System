import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(public http: HttpClient) { }

  private rootUrl: string = "http://localhost:9090/api/user"


  onLogin(username: string, password: string): any {
    const credential = { username: username, password: password }
    return this.http.post(`${this.rootUrl}/authenticate`, credential);
  }

  // onLogout() {
  //   this.isLoggedIn.next(false);
  //   this.log = false;
  // }

  registration = (username: string, password: string, roles: string) => {
    const credential = { username: username, password: password, roles: roles }
    return this.http.post(`${this.rootUrl}/register`, credential);
  }

  loggedIn = (): boolean => {
    return localStorage.getItem('auth-token') !== null;
  }

}
