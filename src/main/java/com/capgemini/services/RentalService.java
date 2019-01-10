package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.RentalTO;

public interface RentalService {
	
	Long findRentalsNo();

	RentalTO saveRental(RentalTO rentalTO);
	RentalTO findRental(Long id);
	RentalTO updateRental(RentalTO rentalTO);
	
	List<RentalTO>findAllRentals();

	void removeRental(Long id);

}
