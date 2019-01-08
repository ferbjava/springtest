package com.capgemini.tos;

import java.util.Calendar;
import java.util.Locale;

public class EmployeeTO {

	private Long id;
	private String firstName;
	private String lastName;
	private Calendar dateBirth;
	private Long positionId;
	private Long departmentId;

	public EmployeeTO() {
		super();
	}

	public EmployeeTO(Long id, String firstName, String lastName, Calendar dateBirth, Long positionId,
			Long departmentId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateBirth = dateBirth;
		this.positionId = positionId;
		this.departmentId = departmentId;
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

	public Calendar getDateBirth() {
		return dateBirth;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public static class EmployeeTOBuilder {

		private Long id;
		private String firstName;
		private String lastName;
		private Calendar dateBirth;
		private Long positionId;
		private Long departmentId;

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

		public EmployeeTOBuilder withDateBirth(Calendar dateBirth) {
			this.dateBirth = dateBirth;
			return this;
		}

		public EmployeeTOBuilder withPositionId(Long positionId) {
			this.positionId = positionId;
			return this;
		}

		public EmployeeTOBuilder withDepartmentId(Long depId) {
			this.departmentId = depId;
			return this;
		}

		public EmployeeTO build() {
			checkBeforeBuild(firstName, lastName, dateBirth);
			return new EmployeeTO(id, firstName, lastName, dateBirth, positionId, departmentId);
		}

		private void checkBeforeBuild(String firstName, String lastName, Calendar dateBirth) {
			boolean isFirstName = false;
			if (firstName != null && !firstName.isEmpty()) {
				isFirstName = true;
			}
			boolean isLastName = false;
			if (lastName != null && !lastName.isEmpty()) {
				isLastName = true;
			}
			boolean isDateBirth = false;
			if (dateBirth != null) {
				isDateBirth = true;
			}
			if (!isFirstName || !isLastName || !isDateBirth) {
				throw new RuntimeException("Invalid 'EMPLOYEE' to be created");
			}
		}
	}

	@Override
	public String toString() {
		Locale locale = Locale.getDefault();
		return "EmployeeTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dateBirth="
				+ dateBirth.get(5) + " " + dateBirth.getDisplayName(Calendar.MONTH, Calendar.LONG, locale) + " "
				+ dateBirth.get(1) + ", positionId=" + positionId + ", departmentId=" + departmentId + "]";
	}

}
