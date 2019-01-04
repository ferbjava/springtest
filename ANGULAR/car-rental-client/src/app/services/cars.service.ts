import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from '../models/car.model';
import { Observable } from 'rxjs';

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
