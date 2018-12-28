package com.capgemini.tos;

public class PositionTO {

	private Long id;
	private String positionName;

	public PositionTO() {
		super();
	}

	public PositionTO(Long id, String positionName) {
		super();
		this.id = id;
		this.positionName = positionName;
	}

	public Long getId() {
		return id;
	}

	public String getPositionName() {
		return positionName;
	}

	public static class PositionTOBuilder {

		private Long id;
		private String positionName;

		public PositionTOBuilder() {
			super();
		}
		
		public PositionTOBuilder withId (Long id) {
			this.id = id;
			return this;
		}
		
		public PositionTOBuilder withPositionName(String positionName) {
			this.positionName = positionName;
			return this;
		}
		
		public PositionTO build() {
			checkBeforeBuild(positionName);
			return new PositionTO(id, positionName);
		}

		private void checkBeforeBuild(String positionName) {
			if(positionName == null || positionName.equals("")) {
				throw new RuntimeException("Invalid 'POSITION' to be created");
			}
		}

	}

}
