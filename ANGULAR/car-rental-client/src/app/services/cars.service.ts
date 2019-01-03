import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Car } from '../models/car.model';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private carsUrl = '//localhost:8080/car';

  constructor(
    private readonly http: HttpClient,
  ) { }

    getAllCars(): Observable<Car[]> {
      return this.http.get<Car[]>(`${this.carsUrl}`);
    }
}
