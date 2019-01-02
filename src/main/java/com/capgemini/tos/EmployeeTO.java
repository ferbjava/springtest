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
			checkBeforeBuild(firstName, lastName, positionId);
			return new EmployeeTO(id, firstName, lastName, positionId);
		}

		private void checkBeforeBuild(String firstName, String lastName, Long position) {
			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			boolean isPositionId = false;
			if (position != null) {
				isPositionId = true;
			}
			if (!isFirstName || !isLastName || !isPositionId) {
				throw new RuntimeException("Invalid 'EMPLOYEE' to be created");
			}
		}
	}

}
