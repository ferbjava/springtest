package com.capgemini.tos;

import java.util.ArrayList;
import java.util.List;

public class PositionTO {

	private Long id;
	private String positionName;
	private List<Long> employeesId = new ArrayList<>();

	public PositionTO() {
		super();
	}

	public PositionTO(Long id, String positionName, List<Long> employeesId) {
		super();
		this.id = id;
		this.positionName = positionName;
		this.employeesId = employeesId;
	}

	public Long getId() {
		return id;
	}

	public String getPositionName() {
		return positionName;
	}

	public List<Long> getEmployeesId() {
		return employeesId;
	}

	public static class PositionTOBuilder {

		private Long id;
		private String positionName;
		private List<Long> employeesId;

		public PositionTOBuilder() {
			super();
		}

		public PositionTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public PositionTOBuilder withPositionName(String positionName) {
			this.positionName = positionName;
			return this;
		}

		public PositionTOBuilder withEmployeesId(List<Long> employeesId) {
			this.employeesId = employeesId;
			return this;
		}

		public PositionTO build() {
			checkBeforeBuild(positionName);
			return new PositionTO(id, positionName, employeesId);
		}

		private void checkBeforeBuild(String positionName) {
			if (positionName == null || positionName.equals("")) {
				throw new RuntimeException("Invalid 'POSITION' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "PositionTO [id=" + id + ", positionName=" + positionName + "]";
	}

}
