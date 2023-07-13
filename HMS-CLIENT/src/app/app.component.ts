import { AfterContentInit, Component, DoCheck, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { AuthService } from './service/auth/auth.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck, AfterContentInit {
  title = 'hms-client';
  sideNavStatus: boolean = false;

  logFlag: boolean = false;

  constructor(public authService: AuthService) {
  }
  ngAfterContentInit(): void {
    // this.isLoggedIn();
  }
  ngDoCheck(): void {
    this.isLoggedIn();
  }
  // ngDoCheck(): void {

  // }

  isLoggedIn = () => {
    if (this.authService.loggedIn() || localStorage.getItem("isLoggedIn")) {
      this.logFlag = true;
    }

    if( localStorage.getItem("isLoggedIn") === null){
      this.logFlag = false;
    }
  }
}
