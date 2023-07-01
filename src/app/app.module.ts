import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhysicianComponent } from './physician/physician.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PatientComponent } from './patient/patient.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NurseComponent } from './nurse/nurse.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { AppointmentlistComponent } from './appointmentlist/appointmentlist.component';
import { NursetableComponent } from './nursetable/nursetable.component';
import { PatienttableComponent } from './patienttable/patienttable.component';
import { PhysicianlistComponent } from './physicianlist/physicianlist.component';
import { DepartmentlistComponent } from './departmentlist/departmentlist.component';
import { DepartmentComponent } from './department/department.component';
@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    PhysicianComponent,
    SidebarComponent,
    DashboardComponent,
    NurseComponent,
    AppointmentComponent,
    AppointmentlistComponent,
    NursetableComponent,
    PatienttableComponent,
    PhysicianlistComponent,
    DepartmentlistComponent,
    DepartmentComponent,],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
