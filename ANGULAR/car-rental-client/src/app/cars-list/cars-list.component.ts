import { Component, OnInit, OnDestroy } from '@angular/core';
import { CarsService } from '../services/cars.service';
import { Car } from '../models/car.model';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.scss']
})
export class CarsListComponent implements OnInit, OnDestroy {

  private readonly destroy$ = new Subject();
  cars: Car[];
  constructor(
    private service: CarsService,
  ) { }

  ngOnInit() {
    this.loadCars();
  }

  loadCars() {
    this.service.getAllCars().subscribe( data => {
      this.cars = data;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
