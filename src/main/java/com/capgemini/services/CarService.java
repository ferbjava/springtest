package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.CarTO;

public interface CarService {
	
	Long findCarNo();

	CarTO saveCar(CarTO carTO);
	CarTO findCar(Long id);
	CarTO updateCar(CarTO carTO);
	
	List<CarTO>findAllCars();

	void removeCar(Long id);
}
