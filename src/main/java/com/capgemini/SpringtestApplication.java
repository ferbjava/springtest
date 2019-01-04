package com.capgemini;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.services.CarService;
import com.capgemini.services.EmployeeService;
import com.capgemini.services.PositionService;
import com.capgemini.tos.CarTO;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.PositionTO;
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
	ApplicationRunner init(CarService carService, PositionService positionService, EmployeeService empService) {
		final DataSource dataSource = new DataSource();
		final List<CarTO> carsList = dataSource.getCarsList();
		final List<PositionTO> positionsList = dataSource.getPositionsList();
		final List<EmployeeTO> employeeList = dataSource.getEmployeeList();

		return args -> {
			carsList.stream().forEach(carTO -> carService.saveCar(carTO));
			carService.findAllCars().forEach(System.out::println);

			positionsList.stream().forEach(positionTO -> positionService.savePosition(positionTO));
			positionService.findAllPositions().forEach(System.out::println);

			employeeList.get(0).setPositionId(positionService.findPosition(1L).getId());
			employeeList.get(1).setPositionId(positionService.findPosition(3L).getId());
			employeeList.stream().forEach(employeeTO -> {
				if (employeeList.indexOf(employeeTO) > 1) {
					employeeTO.setPositionId(positionService.findPosition(2L).getId());
				};
			});
			employeeList.stream().forEach(employeeTO -> empService.saveEmployee(employeeTO));
			empService.findAllEmployees().forEach(System.out::println);
		};
	}
}
