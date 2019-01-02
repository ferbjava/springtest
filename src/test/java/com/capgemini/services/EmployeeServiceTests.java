package com.capgemini.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.EmployeeTO.EmployeeTOBuilder;
import com.capgemini.tos.PositionTO;
import com.capgemini.tos.PositionTO.PositionTOBuilder;
import com.capgemini.util.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class EmployeeServiceTests {

	private DataSource testData;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PositionService positionService;

	@Before
	public void initialize() {
		testData = new DataSource();
	}

	@Test
	@Transactional
	public void shouldAddEmployee() {
		// given
		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);
		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());

		// when
		EmployeeTO savedEmployee = employeeService.saveEmployee(employee01);

		// then
		assertEquals(employee01.getFirstName(), savedEmployee.getFirstName());
		assertEquals(employee01.getLastName(), savedEmployee.getLastName());
	}

	@Test
	@Transactional
	public void shouldFindEmployeesNumber() {
		// given
		final Long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 0L;
		final Long EXPECTED_FINAL_EMPLOYEES_NUMBER = 2L;

		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);

		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());
		EmployeeTO employee02 = testData.getEmployeeList().get(1);
		employee02.setPositionId(savedPosition01.getId());

		// when
		Long initialEmployeesNumber = employeeService.findEmployeeNo();
		employeeService.saveEmployee(employee01);
		employeeService.saveEmployee(employee02);
		Long finalEmployeesNumber = employeeService.findEmployeeNo();

		// then
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNumber);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNumber);
	}

	@Test
	@Transactional
	public void shouldFindEmployeeById() {
		// given
		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);

		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());
		EmployeeTO employee02 = testData.getEmployeeList().get(1);
		employee02.setPositionId(savedPosition01.getId());

		EmployeeTO savedEmployee01 = employeeService.saveEmployee(employee01);
		employeeService.saveEmployee(employee02);

		// when
		EmployeeTO foundedEmployee = employeeService.findEmployee(savedEmployee01.getId());

		// then
		assertEquals(savedEmployee01.getId(), foundedEmployee.getId());
		assertEquals(savedEmployee01.getFirstName(), foundedEmployee.getFirstName());
	}

	@Test
	@Transactional
	public void shouldFindAllEmployees() {
		// given
		final int EXPECTED_EMPLOYEES_NUMBER = 2;
		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);

		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());
		EmployeeTO employee02 = testData.getEmployeeList().get(1);
		employee02.setPositionId(savedPosition01.getId());

		EmployeeTO savedEmployee01 = employeeService.saveEmployee(employee01);
		EmployeeTO savedEmployee02 = employeeService.saveEmployee(employee02);

		// when
		List<EmployeeTO> foundedEmployees = employeeService.findAllEmployees();

		// then
		assertEquals(EXPECTED_EMPLOYEES_NUMBER, foundedEmployees.size());
		assertEquals(savedEmployee01.getId(), foundedEmployees.get(0).getId());
		assertEquals(savedEmployee02.getId(), foundedEmployees.get(1).getId());
	}

	@Test
	@Transactional
	public void shouldUpdateEmployee() {
		// given
		final Long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 0L;
		final Long EXPECTED_FINAL_EMPLOYEES_NUMBER = 2L;
		final String EXPECTED_NEW_FIRST_NAME = "Bozydar";
		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);

		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());
		EmployeeTO employee02 = testData.getEmployeeList().get(1);
		employee02.setPositionId(savedPosition01.getId());

		Long initialEmployeesNumber = employeeService.findEmployeeNo();
		EmployeeTO savedEmployee01 = employeeService.saveEmployee(employee01);
		employeeService.saveEmployee(employee02);

		// when
		EmployeeTO updatedEmployee01 = new EmployeeTOBuilder().withId(savedEmployee01.getId())
				.withFirstName(EXPECTED_NEW_FIRST_NAME).withLastName(savedEmployee01.getLastName())
				.withPositionId(savedEmployee01.getPositionId()).build();
		EmployeeTO updatedAndSavedEMployee01 = employeeService.updateEmployee(updatedEmployee01);
		Long finalEmployeesNumber = employeeService.findEmployeeNo();

		// then
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNumber);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNumber);
		assertEquals(savedEmployee01.getId(), updatedAndSavedEMployee01.getId());
		assertEquals(EXPECTED_NEW_FIRST_NAME, updatedAndSavedEMployee01.getFirstName());
	}

	@Test
	@Transactional
	public void shouldDeleteEmployee() {
		// given
		final Long EXPECTED_INITIAL_EMPLOYEES_NUMBER = 2L;
		final Long EXPECTED_FINAL_EMPLOYEES_NUMBER = 1L;
		PositionTO position01 = new PositionTOBuilder().withPositionName("manager").build();
		PositionTO savedPosition01 = positionService.savePosition(position01);

		EmployeeTO employee01 = testData.getEmployeeList().get(0);
		employee01.setPositionId(savedPosition01.getId());
		EmployeeTO employee02 = testData.getEmployeeList().get(1);
		employee02.setPositionId(savedPosition01.getId());

		EmployeeTO savedEmployee01 = employeeService.saveEmployee(employee01);
		employeeService.saveEmployee(employee02);
		Long initialEmployeesNumber = employeeService.findEmployeeNo();

		// when
		employeeService.removeEmployee(savedEmployee01.getId());
		Long finalEmployeesNumber = employeeService.findEmployeeNo();

		// then
		assertEquals(EXPECTED_INITIAL_EMPLOYEES_NUMBER, initialEmployeesNumber);
		assertEquals(EXPECTED_FINAL_EMPLOYEES_NUMBER, finalEmployeesNumber);
	}

}
