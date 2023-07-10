import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { AuthService } from 'src/app/service/auth/auth.service';
import { TokenStorageService } from 'src/app/service/TokenStorage/token-storage.service';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent {
  username: string = "";
  password: string = "";
  isLoggedIn = new Subject<boolean>();
  log: boolean = false;
  constructor(public authService: AuthService, public route: Router, public tokenStorage: TokenStorageService) { }

  onLogin() {
    this.authService.onLogin(this.username, this.password).subscribe((result: any) => {
      this.isLoggedIn.next(true);
      this.log = true;
      this.tokenStorage.saveToken(result.token)
      localStorage.setItem("username", this.username)
      localStorage.setItem("isLoggedIn", "true");
      this.route.navigate(["Home"])
    }, (err: any) => {
      alert("Incorrect Username or Password")
      this.log = false;
    });
  }

  loggedIn = (): boolean => {
    return this.log;
  }
}
