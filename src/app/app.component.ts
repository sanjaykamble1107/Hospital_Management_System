import { Component, DoCheck, OnInit } from '@angular/core';
import { AuthService } from './service/auth/auth.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck {
  title = 'hms-client';
  sideNavStatus: boolean = false;

  logFlag: boolean = false;

  constructor(private authService: AuthService) {
  }

  ngDoCheck(): void {
    this.logFlag = this.authService.loggedIn();
  }
}
