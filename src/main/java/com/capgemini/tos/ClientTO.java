package com.capgemini.tos;

import java.util.ArrayList;
import java.util.List;

public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private List<Long> rentalsIds = new ArrayList<>();

	public ClientTO() {
		super();
	}

	public ClientTO(Long id, String firstName, String lastName, List<Long> rentalsIds) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.rentalsIds.addAll(rentalsIds);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Long> getRentalsIds() {
		return rentalsIds;
	}
	
	public static class ClientTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private List<Long> rentalsIds = new ArrayList<>();
		
		
		public ClientTOBuilder() {
			super();
		}
		
		public ClientTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}
		
		public ClientTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public ClientTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public ClientTOBuilder withRentalsIds(List<Long> rentalsIds) {
			this.rentalsIds.addAll(rentalsIds);
			return this;
		}
		
		public ClientTO build() {
			checkBeforeBuild(firstName, lastName);
			return new ClientTO(id, firstName, lastName, rentalsIds);
		}

		private void checkBeforeBuild(String firstName, String lastName) {
			if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
				throw new RuntimeException("Invalid 'Client' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "ClientTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", rentalsIds="
				+ rentalsIds + "]";
	}

}
