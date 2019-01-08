import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Car } from '../models/car.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  private mainUrl = '//localhost:8080';
  private carsUrl = this.mainUrl + '/car';

  constructor(
    private readonly http: HttpClient,
  ) { }

    getAllCars(): Observable<Car[]> {
      return this.http.get<Car[]>(`${this.carsUrl}`);
    }

    getSingleCar(id: string) {
      return this.http.get(`${this.carsUrl}/${id}`);
    }

    save(car: any): Observable<any> {
      let result: Observable<Object>;
      if (car.id) {
        result = this.http.put(`${this.carsUrl}`, car);
      } else {
        result = this.http.post(`${this.carsUrl}`, car);
      }
      return result;
    }

    remove(id: string) {
      return this.http.delete(`${this.carsUrl}/${id}`);
    }
}
