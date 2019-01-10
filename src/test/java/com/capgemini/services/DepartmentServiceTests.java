package com.capgemini.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tos.DepartmentTO;
import com.capgemini.tos.PositionTO;
import com.capgemini.tos.DepartmentTO.DepartmentTOBuilder;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.util.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class DepartmentServiceTests {

	private DataSource testData;

	@Autowired
	private DepartmentService depService;

	@Autowired
	private PositionService positionService;

	@Autowired
	private EmployeeService empService;

	@Before
	public void initialize() {
		testData = new DataSource();
	}

	@Test
	@Transactional
	public void shouldAddDepartment() {
		// given
		DepartmentTO givenDepartment = testData.getDepartmentsList().get(0);

		// when
		DepartmentTO savedDepartment = depService.saveDepartment(givenDepartment);

		// then
		assertEquals(givenDepartment.getDepAdress(), savedDepartment.getDepAdress());
	}

	@Test
	@Transactional
	public void shouldFindDepartmentsNumber() {
		// given
		final Long EXPECTED_INITIAL_DEP_NUMBER = 0L;
		final Long EXPECTED_FINAL_DEP_NUMBER = 2L;
		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);

		// when
		Long initialDepartmentsNumber = depService.findDepartmentNo();
		depService.saveDepartment(givenDepartment01);
		depService.saveDepartment(givenDepartment02);
		Long finalDepartmentsNumber = depService.findDepartmentNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEP_NUMBER, initialDepartmentsNumber);
		assertEquals(EXPECTED_FINAL_DEP_NUMBER, finalDepartmentsNumber);
	}

	@Test
	@Transactional
	public void shouldFindDepartmentById() {
		// given
		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);

		DepartmentTO savedDep01 = depService.saveDepartment(givenDepartment01);
		depService.saveDepartment(givenDepartment02);

		// when
		Long dep01Id = savedDep01.getId();
		DepartmentTO foundedDep = depService.findDepartment(dep01Id);

		// then
		assertEquals(savedDep01.getId(), foundedDep.getId());
		assertEquals(savedDep01.getDepAdress(), foundedDep.getDepAdress());
	}

	@Test
	@Transactional
	public void shouldFindAllDepartments() {
		// given
		final Long EXPECTED_DEP_NUMBER = 2L;
		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);

		DepartmentTO savedDep01 = depService.saveDepartment(givenDepartment01);
		DepartmentTO savedDep02 = depService.saveDepartment(givenDepartment02);

		// when
		List<DepartmentTO> foundedDeps = depService.findAllDepartments();

		// then
		assertEquals(EXPECTED_DEP_NUMBER, new Long(foundedDeps.size()));
		assertEquals(savedDep01.getId(), foundedDeps.get(0).getId());
		assertEquals(savedDep01.getDepAdress(), foundedDeps.get(0).getDepAdress());
		assertEquals(savedDep02.getId(), foundedDeps.get(1).getId());
		assertEquals(savedDep02.getDepAdress(), foundedDeps.get(1).getDepAdress());
	}

	@Test
	@Transactional
	public void shouldUpdateDepartment() {
		// given
		final Long EXPECTED_INITIAL_DEP_NUMBER = 0L;
		final Long EXPECTED_FINAL_DEP_NUMBER = 2L;
		final String EXPECTED_NEW_DEP_ADRESS = "Wachock";

		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);

		Long initialDepsNumber = depService.findDepartmentNo();
		DepartmentTO savedDep01 = depService.saveDepartment(givenDepartment01);
		depService.saveDepartment(givenDepartment02);

		// when
		DepartmentTO updatedDep01 = new DepartmentTOBuilder().withId(savedDep01.getId())
				.withDepAdress(EXPECTED_NEW_DEP_ADRESS).withDepTelephone(savedDep01.getDepTelephone())
				.withEmployeesIds(savedDep01.getEmployeesIds()).build();
		DepartmentTO updatedAndSavedDep01 = depService.updateDepartment(updatedDep01);
		Long finalDepsNumber = depService.findDepartmentNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEP_NUMBER, initialDepsNumber);
		assertEquals(EXPECTED_FINAL_DEP_NUMBER, finalDepsNumber);
		assertEquals(savedDep01.getId(), updatedAndSavedDep01.getId());
		assertEquals(EXPECTED_NEW_DEP_ADRESS, updatedAndSavedDep01.getDepAdress());
	}

	@Test
	@Transactional
	public void shouldDeleteDepartment() {
		// given
		final Long EXPECTED_INITIAL_DEP_NUMBER = 2L;
		final Long EXPECTED_FINAL_DEP_NUMBER = 1L;

		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);

		DepartmentTO savedDep01 = depService.saveDepartment(givenDepartment01);
		depService.saveDepartment(givenDepartment02);
		Long initialDepsNumber = depService.findDepartmentNo();

		// when
		depService.removeDepartment(savedDep01.getId());
		Long finalDepsNumber = depService.findDepartmentNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEP_NUMBER, initialDepsNumber);
		assertEquals(EXPECTED_FINAL_DEP_NUMBER, finalDepsNumber);
	}

	@Test
	@Transactional
	public void shouldDeleteDepartmentAndEmployees() {
		// given
		final Long EXPECTED_INITIAL_DEP_NUMBER = 2L;
		final Long EXPECTED_FINAL_DEP_NUMBER = 1L;
		final Long EXPECTED_INITIAL_EMP_NUMBER = 4L;
		final Long EXPECTED_FINAL_EMP_NUMBER = 2L;

		PositionTO givenPosition01 = testData.getPositionsList().get(1);
		PositionTO savedPosition01 = positionService.savePosition(givenPosition01);

		DepartmentTO givenDepartment01 = testData.getDepartmentsList().get(0);
		DepartmentTO givenDepartment02 = testData.getDepartmentsList().get(1);
		DepartmentTO savedDep01 = depService.saveDepartment(givenDepartment01);
		DepartmentTO savedDep02 = depService.saveDepartment(givenDepartment02);

		EmployeeTO givenEmp01 = testData.getEmployeeList().get(0);
		givenEmp01.setPositionId(savedPosition01.getId());
		givenEmp01.setDepartmentId(savedDep01.getId());
		empService.saveEmployee(givenEmp01);
		
		EmployeeTO givenEmp02 = testData.getEmployeeList().get(1);
		givenEmp02.setPositionId(savedPosition01.getId());
		givenEmp02.setDepartmentId(savedDep01.getId());
		empService.saveEmployee(givenEmp02);
		
		EmployeeTO givenEmp03 = testData.getEmployeeList().get(2);
		givenEmp03.setPositionId(savedPosition01.getId());
		givenEmp03.setDepartmentId(savedDep02.getId());
		empService.saveEmployee(givenEmp03);
		
		EmployeeTO givenEmp04 = testData.getEmployeeList().get(3);
		givenEmp04.setPositionId(savedPosition01.getId());
		givenEmp04.setDepartmentId(savedDep02.getId());
		empService.saveEmployee(givenEmp04);

		Long initialDepsNumber = depService.findDepartmentNo();
		Long initialEmpsNumber = empService.findEmployeeNo();

		// when
		depService.removeDepartment(savedDep01.getId());
		Long finalDepsNumber = depService.findDepartmentNo();
		Long finalEmpsNumber = empService.findEmployeeNo();

		// then
		assertEquals(EXPECTED_INITIAL_DEP_NUMBER, initialDepsNumber);
		assertEquals(EXPECTED_FINAL_DEP_NUMBER, finalDepsNumber);
		assertEquals(EXPECTED_INITIAL_EMP_NUMBER, initialEmpsNumber);
		assertEquals(EXPECTED_FINAL_EMP_NUMBER, finalEmpsNumber);
	}

}
