package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.ClientTO;
import com.capgemini.tos.RentalTO;

public interface ClientService {
	
	Long findClientsNo();

	ClientTO saveClient(ClientTO clientTO);
	ClientTO findClient(Long id);
	ClientTO updateClient(ClientTO clientTO);
	ClientTO addRentalToClient(RentalTO rentalTO);
	
	List<ClientTO>findAllClients();

	void removeClient(Long id);

}
