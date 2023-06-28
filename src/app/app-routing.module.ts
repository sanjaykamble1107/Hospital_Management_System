import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { NurseComponent } from './nurse/nurse.component';
import { PatientComponent } from './patient/patient.component';
import { PhysicianComponent } from './physician/physician.component';

const routes: Routes = [{ path: "", redirectTo: "Home", pathMatch: "full" },
{ path: "Home", component: DashboardComponent },
{ path: "Physician", component: PhysicianComponent },
{ path: "Patient", component: PatientComponent },
{path:"Nurse",component:NurseComponent},
{ path: "**", redirectTo: "Home" }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
