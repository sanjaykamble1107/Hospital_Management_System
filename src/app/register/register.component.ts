import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  roles: string = 'admin';


  constructor(public authService: AuthService, public route: Router){}
  onRegister() {
    // Here you can handle the form submission logic, such as making an API call to register the user
    console.log('Username:', this.username);
    console.log('Password:', this.password);
    console.log('Role:', this.roles);

  }
}

