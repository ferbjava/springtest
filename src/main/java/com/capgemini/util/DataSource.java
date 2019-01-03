package com.capgemini.util;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.tos.CarTO;
import com.capgemini.tos.CarTO.CarTOBuilder;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.tos.PositionTO;
import com.capgemini.tos.PositionTO.PositionTOBuilder;

public class DataSource {

	private List<PositionTO> positionsData = new ArrayList<>();
	private List<CarTO> carsData = new ArrayList<>();
	private List<EmployeeTO> employeeData = new ArrayList<>();

	public DataSource() {
		fillPositionsList();
		fillCarsList();
		fillEmployeesList();
	}
	
	public List<PositionTO> getPositionsList() {
		return positionsData;
	}

	public List<CarTO> getCarsList() {
		return carsData;
	}

	public List<EmployeeTO> getEmployeeList() {
		return employeeData;
	}
	
	private void fillPositionsList() {
		positionsData.add(new PositionTOBuilder().withPositionName("manager").build());
		positionsData.add(new PositionTOBuilder().withPositionName("dealer").build());
		positionsData.add(new PositionTOBuilder().withPositionName("accountant").build());
	}

	private void fillCarsList() {
		carsData.add(new CarTOBuilder().withBrand("VW").withType("Golf").withProductionYear(2010).withColor("red")
				.withEngineCapacity(1950).withEnginePower(100).withMileage(150000).build());
		carsData.add(new CarTOBuilder().withBrand("Audi").withType("A4").withProductionYear(2011).withColor("blue")
				.withEngineCapacity(2050).withEnginePower(150).withMileage(200000).build());
		carsData.add(new CarTOBuilder().withBrand("Fiat").withType("Panda").withProductionYear(2012).withColor("black")
				.withEngineCapacity(1000).withEnginePower(60).withMileage(500000).build());
		carsData.add(new CarTOBuilder().withBrand("Deawoo").withType("Matiz").withProductionYear(2013).withColor("yellow")
				.withEngineCapacity(1500).withEnginePower(20).withMileage(300000).build());
		carsData.add(new CarTOBuilder().withBrand("FSO").withType("Polonez").withProductionYear(2014).withColor("white")
				.withEngineCapacity(1800).withEnginePower(500).withMileage(1000000).build());
	}

	private void fillEmployeesList() {
		employeeData.add(new EmployeeTOBuilder().withFirstName("Jan").withLastName("Kowalski").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Adam").withLastName("Nowak").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Piotr").withLastName("Wisniewski").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Andrzej").withLastName("Duda").build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk").build());
	}

}
