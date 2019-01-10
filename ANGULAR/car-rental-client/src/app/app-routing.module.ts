import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CarsListComponent } from './cars-list/cars-list.component';
import { CarEditComponent } from './car-edit/car-edit.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepartmentsComponent } from './departments/departments.component';
import { ClientsComponent } from './clients/clients.component';

const routes: Routes = [
  {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'departments', component: DepartmentsComponent},
  {path: 'clients', component: ClientsComponent},
  {path: 'cars', component: CarsListComponent},
  {path: 'car-edit/:id', component: CarEditComponent},
  {path: 'car-add', component: CarEditComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
