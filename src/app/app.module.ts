import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PhysicianComponent } from './physician/physician.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PatientComponent } from './patient/patient.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NurseComponent } from './nurse/nurse.component';
import { AppointmentComponent } from './appointment/appointment.component';
@NgModule({
  declarations: [
    AppComponent,
    PatientComponent,
    PhysicianComponent,
    SidebarComponent,
    DashboardComponent,
    NurseComponent,
    AppointmentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule, HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
