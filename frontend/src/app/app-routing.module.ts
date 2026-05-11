import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsComponent } from './clients/clients.component';
import { ContratsComponent } from './contrats/contrats.component';
import { LoginComponent } from './login/login.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';
import { PaiementsComponent } from './paiements/paiements.component';
import { AuthGuard } from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'clients',
    component: ClientsComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { roles: ['ROLE_EMPLOYE', 'ROLE_ADMIN'] }
  },
  { path: 'contrats', component: ContratsComponent, canActivate: [AuthGuard] },
  { path: 'paiements', component: PaiementsComponent, canActivate: [AuthGuard] },
  { path: 'not-authorized', component: NotAuthorizedComponent },
  { path: '', redirectTo: 'paiements', pathMatch: 'full' },
  { path: '**', redirectTo: 'paiements' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
