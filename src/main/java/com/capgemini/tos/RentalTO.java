package com.capgemini.tos;

import java.util.Calendar;

public class RentalTO {

	private Long id;
	private Calendar rentalDate;
	private Long clientId;

	public RentalTO() {
		super();
	}

	public RentalTO(Long id, Calendar rentalDate, Long clientId) {
		super();
		this.id = id;
		this.rentalDate = rentalDate;
		this.clientId = clientId;
	}

	public Long getId() {
		return id;
	}

	public Calendar getRentalDate() {
		return rentalDate;
	}

	public Long getClientId() {
		return clientId;
	}

	public static class RentalTOBuilder {

		private Long id;
		private Calendar rentalDate;
		private Long clientId;

		public RentalTOBuilder() {
			super();
		}

		public RentalTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public RentalTOBuilder withRentalDate(Calendar rentalDate) {
			this.rentalDate = rentalDate;
			return this;
		}

		public RentalTOBuilder withClientId(Long clientId) {
			this.clientId = clientId;
			return this;
		}

		public RentalTO build() {
			checkBeforeBuild(rentalDate);
			return new RentalTO(id, rentalDate, clientId);
		}

		private void checkBeforeBuild(Calendar rentalDate) {
			if(rentalDate == null) {
				throw new RuntimeException("Invalid 'Rental' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "RentalTO [id=" + id + ", rentalDate=" + rentalDate + ", clientId=" + clientId + "]";
	}

}
