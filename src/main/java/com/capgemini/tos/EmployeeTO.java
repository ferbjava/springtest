package com.capgemini.tos;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Long positionId;

	public EmployeeTO() {
		super();
	}

	public EmployeeTO(Long id, String firstName, String lastName, Long positionId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.positionId = positionId;
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

	public Long getPositionId() {
		return positionId;
	}
	
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public static class EmployeeTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private Long positionId;

		public EmployeeTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public EmployeeTOBuilder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public EmployeeTOBuilder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public EmployeeTOBuilder withPositionId(Long positionId) {
			this.positionId = positionId;
			return this;
		}

		public EmployeeTO build() {
			checkBeforeBuild(firstName, lastName);
			return new EmployeeTO(id, firstName, lastName, positionId);
		}

		private void checkBeforeBuild(String firstName, String lastName) {
			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			if (!isFirstName || !isLastName) {
				throw new RuntimeException("Invalid 'EMPLOYEE' to be created");
			}
		}
	}

}
