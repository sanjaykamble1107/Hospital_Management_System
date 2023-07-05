import { Component, Input } from '@angular/core';
import { faBuilding, faUserDoctor, faUserNurse, faHouse, faBed, faCalendarCheck, faBedPulse, faChartColumn, faClipboard } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  @Input() sideNavStatus: boolean = false;

  home = faHouse;
  doctor = faUserDoctor;
  nurse = faUserNurse;
  department = faBuilding;
  bed = faBed;
  appointment = faCalendarCheck;
  procedure = faBedPulse;
  affiliated = faClipboard;
  trainedIn = faChartColumn;


}
