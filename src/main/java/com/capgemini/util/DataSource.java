package com.capgemini.util;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.capgemini.tos.CarTO;
import com.capgemini.tos.CarTO.CarTOBuilder;
import com.capgemini.tos.DepartmentTO;
import com.capgemini.tos.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.tos.PositionTO;
import com.capgemini.tos.PositionTO.PositionTOBuilder;

public class DataSource {

	private List<DepartmentTO> departmentsData = new ArrayList<>();
	private List<PositionTO> positionsData = new ArrayList<>();
	private List<CarTO> carsData = new ArrayList<>();
	private List<EmployeeTO> employeeData = new ArrayList<>();

	public DataSource() {
		fillDepartmentsList();
		fillPositionsList();
		fillCarsList();
		fillEmployeesList();
	}

	public List<DepartmentTO> getDepartmentsList() {
		return departmentsData;
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

	private void fillDepartmentsList() {
		departmentsData
				.add(new DepartmentTOBuilder().withDepAdress("Poznan, ul. Glowna").withDepTelephone(700700701).build());
		departmentsData
				.add(new DepartmentTOBuilder().withDepAdress("Warszawa, ul. Marszalkowska").withDepTelephone(700700702).build());
		departmentsData
				.add(new DepartmentTOBuilder().withDepAdress("Krakow, ul. Bracka").withDepTelephone(700700703).build());
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
		carsData.add(new CarTOBuilder().withBrand("Deawoo").withType("Matiz").withProductionYear(2013)
				.withColor("yellow").withEngineCapacity(1500).withEnginePower(20).withMileage(300000).build());
		carsData.add(new CarTOBuilder().withBrand("FSO").withType("Polonez").withProductionYear(2014).withColor("white")
				.withEngineCapacity(1800).withEnginePower(500).withMileage(1000000).build());
	}

	private void fillEmployeesList() {
		employeeData.add(new EmployeeTOBuilder().withFirstName("Jan").withLastName("Kowalski")
				.withDateBirth(new GregorianCalendar(1980, 0, 1)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Adam").withLastName("Nowak")
				.withDateBirth(new GregorianCalendar(1981, 1, 11)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Piotr").withLastName("Wisniewski")
				.withDateBirth(new GregorianCalendar(1982, 2, 21)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Andrzej").withLastName("Duda")
				.withDateBirth(new GregorianCalendar(1983, 3, 30)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Zenon").withLastName("Martyniuk")
				.withDateBirth(new GregorianCalendar(1984, 4, 2)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Przemyslaw").withLastName("Lewandowski")
				.withDateBirth(new GregorianCalendar(1985, 5, 12)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Michal").withLastName("Reiss")
				.withDateBirth(new GregorianCalendar(1986, 6, 22)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Mateusz").withLastName("Buric")
				.withDateBirth(new GregorianCalendar(1987, 7, 3)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Gniewomir").withLastName("Gumny")
				.withDateBirth(new GregorianCalendar(1988, 8, 13)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Rafal").withLastName("Brozek")
				.withDateBirth(new GregorianCalendar(1989, 9, 23)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Kamil").withLastName("Peszko")
				.withDateBirth(new GregorianCalendar(1990, 10, 4)).build());
		employeeData.add(new EmployeeTOBuilder().withFirstName("Cyryl").withLastName("Podolski")
				.withDateBirth(new GregorianCalendar(1991, 11, 14)).build());
	}

}
