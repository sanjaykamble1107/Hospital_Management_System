import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { TokenStorageService } from 'src/app/service/TokenStorage/token-storage.service';
import { AuthService } from '../../service/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  roles: string = 'ADMIN';
  isLoggedIn = new Subject<boolean>();
  log: boolean = false;

  constructor(public authService: AuthService, public route: Router, public tokenStorage: TokenStorageService) { }
  onRegister() {

    this.authService.registration(this.username, this.password, this.roles).subscribe((result: any) => {
      this.isLoggedIn.next(true);
      this.log = true;
      this.tokenStorage.saveToken(result.token)
      localStorage.setItem("username", this.username)
      localStorage.setItem("isLoggedIn", "true");
      alert("Admin created successfully!")
      this.route.navigate(["Home"])
    }, err => {
      alert("Provide all the information")
      this.log = false;
    })
  }

  toLogin() {
    this.route.navigate(['login']);
  }
}

