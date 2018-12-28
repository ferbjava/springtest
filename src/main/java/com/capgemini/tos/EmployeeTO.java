package com.capgemini.tos;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String position;

	public EmployeeTO() {
		super();
	}

	public EmployeeTO(Long id, String firstName, String lastName, String position) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
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

	public String getPosition() {
		return position;
	}

	public static class EmployeeTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private String position;

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

		public EmployeeTOBuilder withPosition(String position) {
			this.position = position;
			return this;
		}

		public EmployeeTO build() {
			checkBeforeBuild(firstName, lastName, position);
			return new EmployeeTO(id, firstName, lastName, position);
		}

		private void checkBeforeBuild(String firstName, String lastName, String position) {
			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			boolean isPosition = false;
			if (position != null && !position.isEmpty()) {
				isPosition = true;
			}
			if (!isFirstName || !isLastName || !isPosition) {
				throw new RuntimeException("Invalid 'EMPLOYEE' to be created");
			}
		}

	}

}
