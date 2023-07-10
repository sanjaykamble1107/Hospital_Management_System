import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { TokenStorageService } from '../TokenStorage/token-storage.service';

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


  loggedIn = (): boolean => {
    return localStorage.getItem('auth-token') !== null;
  }

}
