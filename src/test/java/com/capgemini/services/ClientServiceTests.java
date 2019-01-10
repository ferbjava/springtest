package com.capgemini.services;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.tos.ClientTO;
import com.capgemini.tos.ClientTO.ClientTOBuilder;
import com.capgemini.tos.RentalTO;
import com.capgemini.tos.RentalTO.RentalTOBuilder;
import com.capgemini.util.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class ClientServiceTests {

	private DataSource testData;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private RentalService rentalService;

	@Before
	public void initialize() {
		testData = new DataSource();
	}

	@Test
	@Transactional
	public void shoudAddClient() {
		// given
		final String EXPECTED_FINAL_CLIENT_NAME = testData.getClientList().get(0).getFirstName();
		ClientTO givenCLient = testData.getClientList().get(0);

		// when
		ClientTO savedClient = clientService.saveClient(givenCLient);

		// then
		assertEquals(EXPECTED_FINAL_CLIENT_NAME, savedClient.getFirstName());

	}

	@Test
	@Transactional
	public void shoudFindClientsNumber() {
		// given
		final Long EXPECTED_INITIAL_CLIENTS_NUMBER = 0L;
		final Long EXPECTED_FINAL_CLIENTS_NUMBER = 2L;
		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO givenClient02 = testData.getClientList().get(1);

		// when
		Long initialClientsNumber = clientService.findClientsNo();
		clientService.saveClient(givenClient01);
		clientService.saveClient(givenClient02);
		Long finalClientsNumber = clientService.findClientsNo();

		// then
		assertEquals(EXPECTED_INITIAL_CLIENTS_NUMBER, initialClientsNumber);
		assertEquals(EXPECTED_FINAL_CLIENTS_NUMBER, finalClientsNumber);
	}

	@Test
	@Transactional
	public void shoudFindClientById() {
		// given
		ClientTO givenClient = testData.getClientList().get(0);
		ClientTO savedClient = clientService.saveClient(givenClient);

		// when
		Long clientId = savedClient.getId();
		ClientTO foundedClient = clientService.findClient(clientId);

		// then
		assertEquals(savedClient.getId(), foundedClient.getId());
		assertEquals(savedClient.getFirstName(), foundedClient.getFirstName());
	}

	@Test
	@Transactional
	public void shoudFindAllClients() {
		// given
		final int EXPECTED_CLIENTS_NUMBER = 2;
		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO givenClient02 = testData.getClientList().get(1);

		ClientTO savedClient01 = clientService.saveClient(givenClient01);
		ClientTO savedClient02 = clientService.saveClient(givenClient02);

		// when
		List<ClientTO> foundedClients = clientService.findAllClients();

		// then
		assertEquals(EXPECTED_CLIENTS_NUMBER, foundedClients.size());
		assertEquals(savedClient01.getId(), foundedClients.get(0).getId());
		assertEquals(savedClient02.getId(), foundedClients.get(1).getId());
	}

	@Test
	@Transactional
	public void shoudUpdateClient() {
		// given
		final Long EXPECTED_INITIAL_CLIENTS_NUMBER = 0L;
		final Long EXPECTED_FINAL_CLIENTS_NUMBER = 2L;
		final String EXPECTED_NEW_CLIENT_NAME = "Lech";

		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO givenClient02 = testData.getClientList().get(1);

		Long initialClientsNumber = clientService.findClientsNo();
		ClientTO savedClient01 = clientService.saveClient(givenClient01);
		clientService.saveClient(givenClient02);

		// when
		ClientTO updatedClient = new ClientTOBuilder().withId(savedClient01.getId())
				.withFirstName(EXPECTED_NEW_CLIENT_NAME).withLastName(savedClient01.getLastName())
				.withRentalsIds(savedClient01.getRentalsIds()).build();
		ClientTO updatedAndSavedClient = clientService.updateClient(updatedClient);
		Long finalClientsNumber = clientService.findClientsNo();

		// then
		assertEquals(EXPECTED_INITIAL_CLIENTS_NUMBER, initialClientsNumber);
		assertEquals(EXPECTED_FINAL_CLIENTS_NUMBER, finalClientsNumber);
		assertEquals(savedClient01.getId(), updatedAndSavedClient.getId());
		assertEquals(EXPECTED_NEW_CLIENT_NAME, updatedAndSavedClient.getFirstName());
	}

	@Test
	@Transactional
	public void shoudDeleteClient() {
		// given
		final Long EXPECTED_INITIAL_CLIENTS_NUMBER = 2L;
		final Long EXPECTED_FINAL_CLIENTS_NUMBER = 1L;

		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO givenClient02 = testData.getClientList().get(1);

		ClientTO savedClient01 = clientService.saveClient(givenClient01);
		clientService.saveClient(givenClient02);
		Long initialClientsNumber = clientService.findClientsNo();

		// when
		clientService.removeClient(savedClient01.getId());
		Long finalClientsNumber = clientService.findClientsNo();

		// then
		assertEquals(EXPECTED_INITIAL_CLIENTS_NUMBER, initialClientsNumber);
		assertEquals(EXPECTED_FINAL_CLIENTS_NUMBER, finalClientsNumber);
	}

	@Test
	@Transactional
	public void shoudAddRentalToClient() {
		// given
		final int EXPECTED_INITIAL_RENTALS = 0;
		final int EXPECTED_FINAL_RENTALS = 1;

		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO savedClient01 = clientService.saveClient(givenClient01);
		int initialClientRentals = savedClient01.getRentalsIds().size();

		// when
		RentalTO givenRental = new RentalTOBuilder().withRentalDate(new GregorianCalendar(2019, 0, 10))
				.withClientId(savedClient01.getId()).build();
		ClientTO clientWithRental = clientService.addRentalToClient(givenRental);
		int finalClientRentals = clientWithRental.getRentalsIds().size();

		// then
		assertEquals(EXPECTED_INITIAL_RENTALS, initialClientRentals);
		assertEquals(EXPECTED_FINAL_RENTALS, finalClientRentals);
	}

	@Test
	@Transactional
	public void shoudRemoveClientAndFollowingRentals() {
		// given
		final Long EXPECTED_INITIAL_CLIENTS = 2L;
		final Long EXPECTED_FINAL_CLIENTS = 1L;
		final Long EXPECTED_INITIAL_RENTALS = 4L;
		final Long EXPECTED_FINAL_RENTALS = 2L;

		ClientTO givenClient01 = testData.getClientList().get(0);
		ClientTO savedClient01 = clientService.saveClient(givenClient01);
		ClientTO givenClient02 = testData.getClientList().get(1);
		ClientTO savedClient02 = clientService.saveClient(givenClient02);
		Long initialClientsNumber = clientService.findClientsNo();

		RentalTO givenRental01 = new RentalTOBuilder().withRentalDate(new GregorianCalendar(2019, 0, 10))
				.withClientId(savedClient01.getId()).build();
		RentalTO givenRental02 = new RentalTOBuilder().withRentalDate(new GregorianCalendar(2019, 0, 11))
				.withClientId(savedClient01.getId()).build();
		RentalTO givenRental03 = new RentalTOBuilder().withRentalDate(new GregorianCalendar(2019, 0, 12))
				.withClientId(savedClient02.getId()).build();
		RentalTO givenRental04 = new RentalTOBuilder().withRentalDate(new GregorianCalendar(2019, 0, 13))
				.withClientId(savedClient02.getId()).build();
		clientService.addRentalToClient(givenRental01);
		ClientTO client01With2Rentals = clientService.addRentalToClient(givenRental02);
		clientService.addRentalToClient(givenRental03);
		clientService.addRentalToClient(givenRental04);
		Long initialRentalsNumber = rentalService.findRentalsNo();

		// when
		System.out.println("Przed usunieciem");
		clientService.removeClient(client01With2Rentals.getId());
		System.out.println("Po usunieciu");
		Long finalClientsNumber = clientService.findClientsNo();
		Long finalRentalsNumber = rentalService.findRentalsNo();

		// then
		assertEquals(EXPECTED_INITIAL_CLIENTS, initialClientsNumber);
		assertEquals(EXPECTED_FINAL_CLIENTS, finalClientsNumber);
		assertEquals(EXPECTED_INITIAL_RENTALS, initialRentalsNumber);
		assertEquals(EXPECTED_FINAL_RENTALS, finalRentalsNumber);
	}

}
