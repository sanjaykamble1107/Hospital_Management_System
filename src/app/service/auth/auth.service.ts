import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = new Subject<boolean>();
  log: boolean = false;
  constructor() { }

  onLogin(username: string, password: string): boolean {
    if (username == "admin" && password == "admin") {
      this.isLoggedIn.next(true);
      this.log = true;
      return true;
    }
    return false;
  }

  onLogout() {
    this.isLoggedIn.next(false);
  }

  loggedIn = (): boolean => {
    return this.log;
  }

}
