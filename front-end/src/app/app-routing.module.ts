import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CompanyComponent} from './company/company.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import{AddCompanyComponent} from './add-company/add-company.component';
import { AuthGaurdService } from './service/auth-gaurd.service';

const routes: Routes = [
  { path: '', component: CompanyComponent, canActivate:[AuthGaurdService] },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent, canActivate:[AuthGaurdService] },
  { path: 'add-company', component: AddCompanyComponent, canActivate:[AuthGaurdService]},
  { path: 'add-company/:code', component: AddCompanyComponent, canActivate:[AuthGaurdService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
