import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { faBars } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  fabars = faBars;
  @Output() sideNavToggled = new EventEmitter<boolean>();
  menuStatus: boolean = false;

  username :any =localStorage.getItem("username");
  
  SideNavToggle() {
    this.menuStatus = !this.menuStatus;
    this.sideNavToggled.emit(this.menuStatus);
  }

  constructor(private routes: Router) { }

  logout = () => {
    localStorage.clear();
    this.routes.navigate(["/login"])
  }

}
