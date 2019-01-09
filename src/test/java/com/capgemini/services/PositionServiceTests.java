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

import com.capgemini.tos.PositionTO;
import com.capgemini.tos.PositionTO.PositionTOBuilder;
import com.capgemini.util.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class PositionServiceTests {

	private DataSource testData;

	@Autowired
	private PositionService positionService;

	@Before
	public void initialize() {
		testData = new DataSource();
	}

	@Test
	@Transactional
	public void shouldAddPosition() {
		// given
		final String EXPECTED_POSITION_NAME = "manager";
		PositionTO positionManager = new PositionTOBuilder().withPositionName(EXPECTED_POSITION_NAME).build();

		// when
		PositionTO savedPosition = positionService.savePosition(positionManager);

		// then
		assertEquals(EXPECTED_POSITION_NAME, savedPosition.getPositionName());
	}

	@Test
	@Transactional
	public void shouldFindPositionsNumber() {
		// given
		final Long EXPECTED_INITIAL_POSITION_NUMBER = 0L;
		final Long EXPECTED_FINAL_POSITION_NUMBER = 2L;
		PositionTO position01 = testData.getPositionsList().get(0);
		PositionTO position02 = testData.getPositionsList().get(1);

		// when
		Long initialPositionsNumber = positionService.findPositionNo();
		positionService.savePosition(position01);
		positionService.savePosition(position02);
		Long finalPositionsNumber = positionService.findPositionNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITION_NUMBER, initialPositionsNumber);
		assertEquals(EXPECTED_FINAL_POSITION_NUMBER, finalPositionsNumber);
	}

	@Test
	@Transactional
	public void shouldFindPositionById() {
		// given
		PositionTO position01 = testData.getPositionsList().get(0);
		PositionTO position02 = testData.getPositionsList().get(1);

		positionService.savePosition(position01);
		PositionTO savedPosition02 = positionService.savePosition(position02);

		// when
		PositionTO foundedPosition = positionService.findPosition(savedPosition02.getId());

		// then
		assertEquals(savedPosition02.getId(), foundedPosition.getId());
	}

	@Test
	@Transactional
	public void shouldFindAllPositions() {
		// given
		final int EXPECTED_POSITION_NUMBER = 2;
		PositionTO position01 = testData.getPositionsList().get(0);
		PositionTO position02 = testData.getPositionsList().get(1);

		PositionTO savedPosition01 = positionService.savePosition(position01);
		PositionTO savedPosition02 = positionService.savePosition(position02);

		// when
		List<PositionTO> foundedPositions = positionService.findAllPositions();

		// then
		assertEquals(EXPECTED_POSITION_NUMBER, foundedPositions.size());
		assertEquals(savedPosition01.getPositionName(), foundedPositions.get(0).getPositionName());
		assertEquals(savedPosition02.getPositionName(), foundedPositions.get(1).getPositionName());
	}

	@Test
	@Transactional
	public void shouldUpdatePosition() {
		// given
		final Long EXPECTED_INITIAL_POSITION_NUMBER = 0L;
		final Long EXPECTED_FINAL_POSITION_NUMBER = 2L;
		final String EXPECTED_NEW_POSITION_NAME = "manager01";
		PositionTO position01 = testData.getPositionsList().get(0);
		PositionTO position02 = testData.getPositionsList().get(1);

		Long initialPositionNumber = positionService.findPositionNo();
		PositionTO savedPosition01 = positionService.savePosition(position01);
		positionService.savePosition(position02);

		// when
		PositionTO updatedPosition01 = new PositionTOBuilder().withId(savedPosition01.getId())
				.withPositionName(EXPECTED_NEW_POSITION_NAME).withEmployeesId(savedPosition01.getEmployeesId()).build();
		PositionTO updatedAndSavedPosition01 = positionService.updatePosition(updatedPosition01);
		Long finalPositionNumber = positionService.findPositionNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITION_NUMBER, initialPositionNumber);
		assertEquals(EXPECTED_FINAL_POSITION_NUMBER, finalPositionNumber);
		assertEquals(savedPosition01.getId(), updatedAndSavedPosition01.getId());
		assertEquals(EXPECTED_NEW_POSITION_NAME, updatedAndSavedPosition01.getPositionName());
	}

	@Test
	@Transactional
	public void shouldDeletePosition() {
		// given
		final Long EXPECTED_INITIAL_POSITION_NUMBER = 2L;
		final Long EXPECTED_FINAL_POSITION_NUMBER = 1L;
		PositionTO position01 = testData.getPositionsList().get(0);
		PositionTO position02 = testData.getPositionsList().get(1);

		PositionTO savedPosition01 = positionService.savePosition(position01);
		positionService.savePosition(position02);

		// when
		Long initialPositionNumber = positionService.findPositionNo();
		positionService.removePosition(savedPosition01.getId());
		Long finalPositionNumber = positionService.findPositionNo();

		// then
		assertEquals(EXPECTED_INITIAL_POSITION_NUMBER, initialPositionNumber);
		assertEquals(EXPECTED_FINAL_POSITION_NUMBER, finalPositionNumber);
	}

}
