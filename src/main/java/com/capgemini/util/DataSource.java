package com.capgemini.util;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.tos.CarTO;
import com.capgemini.tos.CarTO.CarTOBuilder;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.EmployeeTO.EmployeeTOBuilder;

public class DataSource {

	private List<CarTO> carsData = new ArrayList<>();
	private List<EmployeeTO> employeeData = new ArrayList<>();

	public DataSource() {
		fillCarsList();
		fillEmployeesList();
	}

	public List<CarTO> getCarsList() {
		return carsData;
	}

	public List<EmployeeTO> getEmployeeList() {
		return employeeData;
	}

	private void fillCarsList() {
		carsData.add(new CarTOBuilder().withBrand("VW").withType("Golf").withProductionYear(2010).withColor("red")
				.withEngineCapacity(1950).withEnginePower(100).withMileage(150000).build());
		carsData.add(new CarTOBuilder().withBrand("Audi").withType("A4").withProductionYear(2010).withColor("blue")
				.withEngineCapacity(2050).withEnginePower(150).withMileage(200000).build());
	}

	private void fillEmployeesList() {
		employeeData.add(new EmployeeTOBuilder().withFirstName("Jan").withLastName("Kowalski").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Adam").withLastName("Nowak").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Piotr").withLastName("Wisniewski").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Andrzej").withLastName("Duda").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk").build());
	}

}
