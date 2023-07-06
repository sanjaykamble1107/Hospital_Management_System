import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.css']
})
export class AdminloginComponent {


  username: string = "";
  password: string = "";

  constructor(public authService: AuthService, public route: Router) { }


  onLogin() {
    let flag = this.authService.onLogin(this.username, this.password);
    if (flag) {
      this.route.navigate(["Home"])
      localStorage.setItem("username", this.username);
      localStorage.setItem("password", this.password);
    }
  }
}
