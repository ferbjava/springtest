package com.capgemini.tos;

import java.util.ArrayList;
import java.util.List;

public class DepartmentTO {

	private Long id;
	private String depAdress;
	private Integer depTelephone;
	private List<Long> employeesIds = new ArrayList<>();

	public DepartmentTO() {
		super();
	}

	public DepartmentTO(Long id, String depAdress, Integer depTelephone, List<Long> employeesIds) {
		super();
		this.id = id;
		this.depAdress = depAdress;
		this.depTelephone = depTelephone;
		this.employeesIds.addAll(employeesIds);
	}

	public Long getId() {
		return id;
	}

	public String getDepAdress() {
		return depAdress;
	}

	public Integer getDepTelephone() {
		return depTelephone;
	}

	public List<Long> getEmployeesIds() {
		return employeesIds;
	}

	public static class DepartmentTOBuilder {

		private Long id;
		private String depAdress;
		private Integer depTelephone;
		private List<Long> employeesIds = new ArrayList<>();

		public DepartmentTOBuilder() {
			super();
		}

		public DepartmentTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public DepartmentTOBuilder withDepAdress(String depAdress) {
			this.depAdress = depAdress;
			return this;
		}

		public DepartmentTOBuilder withDepTelephone(Integer depTelephone) {
			this.depTelephone = depTelephone;
			return this;
		}

		public DepartmentTOBuilder withEmployeesIds(List<Long> employeesIds) {
			this.employeesIds.addAll(employeesIds);
			return this;
		}

		public DepartmentTO build() {
			checkBeforeBuild(depAdress, depTelephone);
			return new DepartmentTO(id, depAdress, depTelephone, employeesIds);
		}

		private void checkBeforeBuild(String depAdress, Integer depTelephone) {
			if (depAdress == null || depAdress.isEmpty() || depAdress == null) {
				throw new RuntimeException("Invalid 'DEPARTMENT' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "DepartmentTO [id=" + id + ", depAdress=" + depAdress + ", depTelephone=" + depTelephone
				+ ", employeesIds=" + employeesIds + "]";
	}

}
