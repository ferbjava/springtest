import { Component, OnInit, OnDestroy } from '@angular/core';
import { CarsService } from '../services/cars.service';
import { Car } from '../models/car.model';
import { Subject } from 'rxjs';
import {takeUntil } from 'rxjs/operators';
import { GiphyService } from '../services/giphy.service';

@Component({
  selector: 'app-cars-list',
  templateUrl: './cars-list.component.html',
  styleUrls: ['./cars-list.component.scss']
})
export class CarsListComponent implements OnInit, OnDestroy {

  private readonly destroy$ = new Subject();
  cars: Car[];

  constructor(
    private carsService: CarsService,
    private giphyService: GiphyService,
  ) { }

  ngOnInit() {
    this.loadCars();
  }

  loadCars() {
    this.carsService.getAllCars()
    .pipe(takeUntil(this.destroy$))
    .subscribe( data => {
      this.cars = data;
      for (const car of this.cars) {
        this.giphyService.get(car.brand)
          .pipe(takeUntil(this.destroy$))
          .subscribe(url => car.giphyUrl = url);
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
