package com.capgemini;

import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.capgemini.services.CarService;
import com.capgemini.services.DepartmentService;
import com.capgemini.services.EmployeeService;
import com.capgemini.services.PositionService;
import com.capgemini.tos.CarTO;
import com.capgemini.tos.DepartmentTO;
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
	@Profile("mysql")
	ApplicationRunner init(CarService carService, PositionService positionService, DepartmentService depService,
			EmployeeService empService) {

		final DataSource dataSource = new DataSource();
		final List<CarTO> carsList = dataSource.getCarsList();
		final List<DepartmentTO> departmentsList = dataSource.getDepartmentsList();
		final List<PositionTO> positionsList = dataSource.getPositionsList();
		final List<EmployeeTO> employeeList = dataSource.getEmployeeList();

		return args -> {
			carsList.stream().forEach(carTO -> carService.saveCar(carTO));
			carService.findAllCars().forEach(System.out::println);

			positionsList.stream().forEach(positionTO -> positionService.savePosition(positionTO));
			positionService.findAllPositions().forEach(System.out::println);

			departmentsList.stream().forEach(depTO -> depService.saveDepartment(depTO));
			depService.findAllDepartmetns().forEach(System.out::println);

			employeeList.get(0).setPositionId(positionService.findPosition(1L).getId());
			employeeList.get(1).setPositionId(positionService.findPosition(3L).getId());
			employeeList.get(2).setPositionId(positionService.findPosition(2L).getId());
			employeeList.get(3).setPositionId(positionService.findPosition(2L).getId());
			employeeList.get(4).setPositionId(positionService.findPosition(1L).getId());
			employeeList.get(5).setPositionId(positionService.findPosition(3L).getId());
			employeeList.get(6).setPositionId(positionService.findPosition(2L).getId());
			employeeList.get(7).setPositionId(positionService.findPosition(2L).getId());
			employeeList.get(8).setPositionId(positionService.findPosition(1L).getId());
			employeeList.get(9).setPositionId(positionService.findPosition(3L).getId());
			employeeList.get(10).setPositionId(positionService.findPosition(2L).getId());
			employeeList.get(11).setPositionId(positionService.findPosition(2L).getId());

			employeeList.get(0).setDepartmentId(depService.findDepartment(1L).getId());
			employeeList.get(1).setDepartmentId(depService.findDepartment(1L).getId());
			employeeList.get(2).setDepartmentId(depService.findDepartment(1L).getId());
			employeeList.get(3).setDepartmentId(depService.findDepartment(1L).getId());
			employeeList.get(4).setDepartmentId(depService.findDepartment(2L).getId());
			employeeList.get(5).setDepartmentId(depService.findDepartment(2L).getId());
			employeeList.get(6).setDepartmentId(depService.findDepartment(2L).getId());
			employeeList.get(7).setDepartmentId(depService.findDepartment(2L).getId());
			employeeList.get(8).setDepartmentId(depService.findDepartment(3L).getId());
			employeeList.get(9).setDepartmentId(depService.findDepartment(3L).getId());
			employeeList.get(10).setDepartmentId(depService.findDepartment(3L).getId());
			employeeList.get(11).setDepartmentId(depService.findDepartment(3L).getId());

			employeeList.stream().forEach(employeeTO -> empService.saveEmployee(employeeTO));
			empService.findAllEmployees().forEach(System.out::println);
		};
	}
}
