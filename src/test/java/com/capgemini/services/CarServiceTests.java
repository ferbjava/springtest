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

import com.capgemini.tos.CarTO;
import com.capgemini.tos.CarTO.CarTOBuilder;
import com.capgemini.util.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest (properties = "spring.profiles.active=hsql")
public class CarServiceTests {

	private DataSource testData;

	@Autowired
	private CarService carService;

	@Before
	public void initialize() {
		testData = new DataSource();
	}

	@Test
	@Transactional
	public void shouldAddCar() {
		// given
		final String EXPECTED_FINAL_CAR_TYPE = testData.getCarsList().get(0).getType();
		CarTO givenCar = testData.getCarsList().get(0);

		// when
		CarTO savedCar = carService.saveCar(givenCar);

		// then
		assertEquals(EXPECTED_FINAL_CAR_TYPE, savedCar.getType());

	}

	@Test
	@Transactional
	public void shouldFindCarsNumber() {
		// given
		final Long EXPECTED_INITIAL_CARS_NUMBER = 0L;
		final Long EXPECTED_FINAL_CARS_NUMBER = 2L;

		CarTO givenCar01 = testData.getCarsList().get(0);
		CarTO givenCar02 = testData.getCarsList().get(1);

		// when
		Long initialCarNumber = carService.findCarNo();
		carService.saveCar(givenCar01);
		carService.saveCar(givenCar02);
		Long finalCarNumber = carService.findCarNo();

		// then
		assertEquals(EXPECTED_INITIAL_CARS_NUMBER, initialCarNumber);
		assertEquals(EXPECTED_FINAL_CARS_NUMBER, finalCarNumber);

	}

	@Test
	@Transactional
	public void shouldFindCarById() {
		// given
		CarTO givenCar01 = testData.getCarsList().get(0);
		CarTO savedCar01 = carService.saveCar(givenCar01);

		// when
		Long carId = savedCar01.getId();
		CarTO foundCar = carService.findCar(carId);

		// then
		assertEquals(savedCar01.getId(), foundCar.getId());
		assertEquals(savedCar01.getType(), foundCar.getType());

	}

	@Test
	@Transactional
	public void shouldFindAllCars() {
		// given
		final Long EXPECTED_CARS_NUMBER = 2L;
		CarTO givenCar01 = testData.getCarsList().get(0);
		CarTO givenCar02 = testData.getCarsList().get(1);

		CarTO savedCar01 = carService.saveCar(givenCar01);
		CarTO savedCar02 = carService.saveCar(givenCar02);

		// when
		List<CarTO> foundedCars = carService.findAllCars();

		// then
		assertEquals(EXPECTED_CARS_NUMBER, new Long(foundedCars.size()));
		assertEquals(savedCar01.getId(), foundedCars.get(0).getId());
		assertEquals(savedCar02.getId(), foundedCars.get(1).getId());

	}

	@Test
	@Transactional
	public void shouldUpdateCar() {
		// given
		final Long EXPECTED_INITIAL_CARS_NUMBER = 0L;
		final Long EXPECTED_FINAL_CARS_NUMBER = 2L;
		final String EXPECTED_NEW_CAR_COLOR = "white";

		CarTO givenCar01 = testData.getCarsList().get(0);
		CarTO givenCar02 = testData.getCarsList().get(1);

		Long initialCarsNumber = carService.findCarNo();
		CarTO savedCar01 = carService.saveCar(givenCar01);
		carService.saveCar(givenCar02);

		// when
		CarTO updatedCar01 = new CarTOBuilder().withId(savedCar01.getId()).withBrand(savedCar01.getBrand())
				.withType(savedCar01.getType()).withProductionYear(savedCar01.getProductionYear())
				.withEngineCapacity(savedCar01.getEngineCapacity()).withEnginePower(savedCar01.getEnginePower())
				.withMileage(savedCar01.getMileage()).withColor(EXPECTED_NEW_CAR_COLOR).build();
		CarTO updatedAndSavedCar = carService.updateCar(updatedCar01);
		Long finalCarsNumber = carService.findCarNo();

		// then
		assertEquals(EXPECTED_INITIAL_CARS_NUMBER, initialCarsNumber);
		assertEquals(EXPECTED_FINAL_CARS_NUMBER, finalCarsNumber);
		assertEquals(savedCar01.getId(), updatedAndSavedCar.getId());
		assertEquals(EXPECTED_NEW_CAR_COLOR, updatedAndSavedCar.getColor());
	}

	@Test
	@Transactional
	public void shouldDeleteCar() {
		// given
		final Long EXPECTED_INITIAL_CARS_NUMBER = 2L;
		final Long EXPECTED_FINAL_CARS_NUMBER = 1L;

		CarTO givenCar01 = testData.getCarsList().get(0);
		CarTO givenCar02 = testData.getCarsList().get(1);

		CarTO savedCar01 = carService.saveCar(givenCar01);
		carService.saveCar(givenCar02);
		Long initialCarsNumber = carService.findCarNo();

		// when
		carService.removeCar(savedCar01.getId());
		Long finalCarsNumber = carService.findCarNo();

		// then
		assertEquals(EXPECTED_INITIAL_CARS_NUMBER, initialCarsNumber);
		assertEquals(EXPECTED_FINAL_CARS_NUMBER, finalCarsNumber);
	}

}
