import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
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
const routes: Routes = [{ path: "", redirectTo: "Home", pathMatch: "full" },
{ path: "Home", component: DashboardComponent },
{ path: "Physician", component: PhysicianComponent },
{ path: "Patient", component: PatientComponent },
{ path: "Nurse", component: NurseComponent },
{ path: "UpdateNurse/:id", component: NurseComponent },
{ path: "Appointment", component: AppointmentComponent },
{ path: "Department", component: DepartmentComponent },
{ path: "Procedure", component: ProcedureComponent },
{ path: "TrainedIn", component: TrainedInComponent },
{ path: "AffiliatedWith", component: AffiliatedwithComponent },
{ path: "AppointmentList", component: AppointmentlistComponent },
{ path: "PatientList", component: PatienttableComponent },
{ path: "NurseList", component: NursetableComponent },
{ path: "PhysicianList", component: PhysicianlistComponent },
{ path: "DepartmentList", component: DepartmentlistComponent },
{ path: "ProcedureList", component: ProcedurelistComponent },
{ path: "updatephysician/:employeeId", component: PhysicianComponent },
{ path: "**", redirectTo: "Home" }];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
