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
import { AdminloginComponent } from './components/adminlogin/adminlogin.component';
import { AuthGuard } from './auth/auth.guard';
const routes: Routes = [{ path: "", redirectTo: "login", pathMatch: "full" },
{ path: "login", component: AdminloginComponent },
{ path: "Home", component: DashboardComponent, canActivate: [AuthGuard] },
{ path: "Physician", component: PhysicianComponent, canActivate: [AuthGuard] },
{ path: "Patient", component: PatientComponent, canActivate: [AuthGuard] },
{ path: "Nurse", component: NurseComponent, canActivate: [AuthGuard] },
{ path: "UpdateNurse/:id", component: NurseComponent, canActivate: [AuthGuard] },
{ path: "Appointment", component: AppointmentComponent, canActivate: [AuthGuard] },
{ path: "Department", component: DepartmentComponent, canActivate: [AuthGuard] },
{ path: "Procedure", component: ProcedureComponent, canActivate: [AuthGuard] },
{ path: "TrainedIn", component: TrainedInComponent, canActivate: [AuthGuard] },
{ path: "AffiliatedWith", component: AffiliatedwithComponent, canActivate: [AuthGuard] },
{ path: "AppointmentList", component: AppointmentlistComponent, canActivate: [AuthGuard] },
{ path: "PatientList", component: PatienttableComponent, canActivate: [AuthGuard] },
{ path: "NurseList", component: NursetableComponent, canActivate: [AuthGuard] },
{ path: "PhysicianList", component: PhysicianlistComponent, canActivate: [AuthGuard] },
{ path: "DepartmentList", component: DepartmentlistComponent, canActivate: [AuthGuard] },
{ path: "ProcedureList", component: ProcedurelistComponent, canActivate: [AuthGuard] },
{ path: "updatephysician/:employeeId", component: PhysicianComponent, canActivate: [AuthGuard] },
{ path: "updatePatient/:ssn", component: PatientComponent, canActivate: [AuthGuard] },
{ path: "updateDepartment/:deptId", component: DepartmentComponent, canActivate: [AuthGuard] },
{ path: "updateProcedure/:code", component: ProcedureComponent, canActivate: [AuthGuard] },
{ path:"updateAppointment/:appointmentId",component:AppointmentComponent,canActivate: [AuthGuard]},
{ path: "**", redirectTo: "Home" }];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
