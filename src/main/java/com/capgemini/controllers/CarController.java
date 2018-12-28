package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.services.CarService;
import com.capgemini.tos.CarTO;

@Controller
@ResponseBody
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CarTO addNewCar(@RequestBody CarTO carTO){
		return carService.saveCar(carTO);
	}
	
	@GetMapping(value = "/car/{id}")
	public CarTO showCarDetails(@PathVariable("id") int id) {
		return carService.findCar(new Long(id));
	}
	
	@GetMapping(value = "/car")
	public List<CarTO> showAllCars() {
		return carService.findAllCars();
	}
	
	@PutMapping(value = "/car/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CarTO updateCarDetails(@RequestBody CarTO carTO) {
		return carService.updateCar(carTO);
	}
	
	@DeleteMapping(value = "/car/{id}")
	public void removeCar(@PathVariable("id") int id) {
		carService.removeCar(new Long(id));
	}

}
