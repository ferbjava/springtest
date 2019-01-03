package com.capgemini.tos;

public class CarTO {

	private Long id;
	private String brand;
	private String type;
	private Integer productionYear;
	private String color;
	private Integer engineCapacity;
	private Integer enginePower;
	private Integer mileage;

	public CarTO() {
		super();
	}

	public CarTO(Long id, String brand, String type, Integer productionYear, String color, Integer engineCapacity,
			Integer enginePower, Integer mileage) {
		super();
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.productionYear = productionYear;
		this.color = color;
		this.engineCapacity = engineCapacity;
		this.enginePower = enginePower;
		this.mileage = mileage;
	}

	public Long getId() {
		return id;
	}

	public String getBrand() {
		return brand;
	}

	public String getType() {
		return type;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public String getColor() {
		return color;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public Integer getMileage() {
		return mileage;
	}

	public static class CarTOBuilder {

		private Long id;
		private String brand;
		private String type;
		private Integer productionYear;
		private String color;
		private Integer engineCapacity;
		private Integer enginePower;
		private Integer mileage;

		public CarTOBuilder() {
			super();
		}

		public CarTOBuilder withId(Long id) {
			this.id = id;
			return this;
		}

		public CarTOBuilder withBrand(String brand) {
			this.brand = brand;
			return this;
		}

		public CarTOBuilder withType(String type) {
			this.type = type;
			return this;
		}

		public CarTOBuilder withProductionYear(Integer productionYear) {
			this.productionYear = productionYear;
			return this;
		}

		public CarTOBuilder withColor(String color) {
			this.color = color;
			return this;
		}

		public CarTOBuilder withEngineCapacity(Integer engineCapacity) {
			this.engineCapacity = engineCapacity;
			return this;
		}

		public CarTOBuilder withEnginePower(Integer enginePower) {
			this.enginePower = enginePower;
			return this;
		}

		public CarTOBuilder withMileage(Integer mileage) {
			this.mileage = mileage;
			return this;
		}

		public CarTO build() {
			checkBeforeBuild(brand, type, productionYear, color, engineCapacity, enginePower, mileage);
			return new CarTO(id, brand, type, productionYear, color, engineCapacity, enginePower, mileage);
		}

		private void checkBeforeBuild(String brand, String type, Integer productionYear, String color,
				Integer engineCapacity, Integer enginePower, Integer mileage) {
			boolean isBrand = false;
			if (brand != null && !brand.isEmpty()) {
				isBrand = true;
			}
			boolean isType = false;
			if (type != null && !type.isEmpty()) {
				isType = true;
			}
			boolean isProductionYear = false;
			if (productionYear != null) {
				isProductionYear = true;
			}
			boolean isColor = false;
			if (color != null && !color.isEmpty()) {
				isColor = true;
			}
			boolean isEngineCapacity = false;
			if (engineCapacity != null) {
				isEngineCapacity = true;
			}
			boolean isEnginePower = false;
			if (enginePower != null) {
				isEnginePower = true;
			}
			boolean isMileage = false;
			if (mileage != null) {
				isMileage = true;
			}
			if (!isBrand || !isType || !isProductionYear || !isProductionYear || !isColor || !isEngineCapacity
					|| !isEnginePower || !isMileage) {
				throw new RuntimeException("Invalid 'CAR' to be created");
			}
		}
	}

	@Override
	public String toString() {
		return "CarTO [id=" + id + ", brand=" + brand + ", type=" + type + ", productionYear=" + productionYear
				+ ", color=" + color + ", engineCapacity=" + engineCapacity + ", enginePower=" + enginePower
				+ ", mileage=" + mileage + "]";
	}

}
