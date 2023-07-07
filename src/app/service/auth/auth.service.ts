import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = new Subject<boolean>();
  log: boolean = false;
  http: any;
  constructor() { }

  private rootUrl: string = "http://localhost:9090/api/login"


  onLogin(username: string, password: string): boolean {
    if (username == "admin" && password == "admin") {
      const body = { username, password }

      this.isLoggedIn.next(true);
      this.log = true;
      return true;
    }
    return false;
  }

  onLogout() {
    this.isLoggedIn.next(false);
    this.log = false;
  }

  loggedIn = (): boolean => {
    return this.log;
  }

}
