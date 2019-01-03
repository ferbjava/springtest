package com.capgemini;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.services.CarService;
import com.capgemini.tos.CarTO;
import com.capgemini.util.DataSource;

@SpringBootApplication
public class SpringtestApplication {

	public static void main(String[] args) {
		// Uncomment line below to use mysql database (default database name =
		// car_rental, user = rental_admin, pass = admin)
		// you can change this in application-mysql.properties
		System.setProperty("spring.profile.active", "mysql");
		SpringApplication.run(SpringtestApplication.class, args);
	}

	@Bean
	ApplicationRunner init(CarService carService) {
		final DataSource dataSource = new DataSource();
		final List<CarTO> carsList = dataSource.getCarsList();

		return args -> {
			carsList.stream().forEach(carTO -> carService.saveCar(carTO));
			carService.findAllCars().forEach(System.out::println);
		};
	}
}
