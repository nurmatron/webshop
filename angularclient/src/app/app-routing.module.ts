import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EmployeeLoginComponent} from "./employee-components/employee-login/employee-login.component";
import {CustomerLoginComponent} from "./customer-components/customer-login/customer-login.component";
import {EmployeePageComponent} from "./employee-components/employee-page/employee-page.component";

const routes: Routes = [
  {path : 'employee', component : EmployeeLoginComponent },
  {path : "", component : CustomerLoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
