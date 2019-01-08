import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { CarsService } from '../services/cars.service';
import { GiphyService } from '../services/giphy.service';
import { NgForm, FormGroup, FormControl, Validators } from '@angular/forms';
import { Car } from '../models/car.model';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-car-edit',
  templateUrl: './car-edit.component.html',
  styleUrls: ['./car-edit.component.scss']
})
export class CarEditComponent implements OnInit, OnDestroy {

  private readonly destroy$ = new Subject();
  car: any = {};

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private carService: CarsService,
    private giphyService: GiphyService
  ) { }

  ngOnInit() {
    this.loadCar();
  }

  loadCar(): void {
    this.route.params
    .pipe(takeUntil(this.destroy$))
    .subscribe(params => {
      const id = params['id'];
      if (id) {
        this.carService.getSingleCar(id)
        .pipe(takeUntil(this.destroy$))
        .subscribe((car: Car) => {
          if (car) {
            this.car = car;
            this.giphyService.get(this.car.brand).subscribe(url => this.car.giphyUrl = url);
          } else {
            console.log(`Car with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  gotoList(): void {
    this.router.navigate(['/cars']);
  }

  save(form: NgForm) {
    this.carService.save(form)
    .pipe(takeUntil(this.destroy$))
    .subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(id: string) {
    this.carService.remove(id)
    .pipe(takeUntil(this.destroy$))
    .subscribe(results => {
      this.gotoList();
    }, error => console.error(error));
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

}
