import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NurseComponent } from './nurse/nurse.component';
import { PatientComponent } from './patient/patient.component';
import { PhysicianComponent } from './physician/physician.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { AppointmentlistComponent } from './appointmentlist/appointmentlist.component';
import { PatienttableComponent } from './patienttable/patienttable.component';
import { NursetableComponent } from './nursetable/nursetable.component';
import { PhysicianlistComponent } from './physicianlist/physicianlist.component';
import { DepartmentlistComponent } from './departmentlist/departmentlist.component';
import { DepartmentComponent } from './department/department.component';
import { ProcedureComponent } from './procedure/procedure.component';
import { ProcedurelistComponent } from './procedurelist/procedurelist.component';
import { TrainedInComponent } from './trainedin/trained-in.component';
import { UpdatephysicianComponent } from './updatephysician/updatephysician.component';
const routes: Routes = [{ path: "", redirectTo: "Home", pathMatch: "full" },
{ path: "Home", component: DashboardComponent },
{ path: "Physician", component: PhysicianComponent },
{ path: "Patient", component: PatientComponent },
{ path: "Nurse", component: NurseComponent },
{ path: "Appointment", component: AppointmentComponent },
{path:"Department",component:DepartmentComponent},
{path:"Procedure",component:ProcedureComponent},
{path:"TrainedIn",component:TrainedInComponent},
{ path: "AppointmentList", component: AppointmentlistComponent },
{ path: "PatientList", component: PatienttableComponent },
{ path: "NurseList", component: NursetableComponent },
{ path: "PhysicianList",component:PhysicianlistComponent},
{path:"DepartmentList",component:DepartmentlistComponent},
{path:"ProcedureList",component:ProcedurelistComponent},
{ path:"updatephysician/:employeeId",component:UpdatephysicianComponent },
{ path: "**", redirectTo: "Home" }];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
