import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AffiliatedwithComponent } from './components/affiliatedwith/affiliatedwith.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { AppointmentlistComponent } from './components/appointmentlist/appointmentlist.component';
import { DepartmentComponent } from './components/department/department.component';
import { DepartmentlistComponent } from './components/departmentlist/departmentlist.component';
import { NurseComponent } from './components/nurse/nurse.component';
import { NursetableComponent } from './components/nurselist/nurselist.component';
import { PatientComponent } from './components/patient/patient.component';
import { PatienttableComponent } from './components/patienttable/patienttable.component';
import { TrainedInComponent } from './components/trainedin/trained-in.component';
import { PhysicianComponent } from './components/physician/physician.component';
import { PhysicianlistComponent } from './components/physicianlist/physicianlist.component';
import { ProcedureComponent } from './components/procedure/procedure.component';
import { ProcedurelistComponent } from './components/procedurelist/procedurelist.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { HeaderComponent } from './components/header/header.component';
import { AdminloginComponent } from './components/adminlogin/adminlogin.component';
import { RegisterComponent } from './register/register.component';

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
    DepartmentComponent,
    ProcedureComponent,
    ProcedurelistComponent,
    TrainedInComponent,
    AffiliatedwithComponent,
    HeaderComponent,
    AdminloginComponent,
    RegisterComponent
  ],
  imports: [
    FontAwesomeModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
