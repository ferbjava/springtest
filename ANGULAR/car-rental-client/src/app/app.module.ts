import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CarsListComponent } from './cars-list/cars-list.component';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonModule, MatMenuModule} from '@angular/material';
import { MatCardModule} from '@angular/material';
import { MatInputModule} from '@angular/material';
import { MatListModule} from '@angular/material';
import { MatToolbarModule} from '@angular/material';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { CarEditComponent } from './car-edit/car-edit.component';
import { FormsModule } from '@angular/forms';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DepartmentsComponent } from './departments/departments.component';
import { ClientsComponent } from './clients/clients.component';

@NgModule({
  declarations: [
    AppComponent,
    CarsListComponent,
    CarEditComponent,
    DashboardComponent,
    DepartmentsComponent,
    ClientsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,

    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatMenuModule,

    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
