package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.CarEntity;
import com.capgemini.tos.CarTO;
import com.capgemini.tos.CarTO.CarTOBuilder;

public class CarMapper {

	public static CarTO toCarTO(CarEntity entity) {
		if (entity == null) {
			return null;
		}
		return new CarTOBuilder().withId(entity.getId()).withBrand(entity.getBrand()).withType(entity.getType())
				.withProductionYear(entity.getProductionYear()).withColor(entity.getColor())
				.withEngineCapacity(entity.getEngineCapacity()).withEnginePower(entity.getEnginePower())
				.withMileage(entity.getMileage()).build();
	}

	public static CarEntity toCarEntity(CarTO carTO) {
		if (carTO == null) {
			return null;
		}
		return new CarEntity(carTO.getId(), carTO.getBrand(), carTO.getType(), carTO.getProductionYear(),
				carTO.getColor(), carTO.getEngineCapacity(), carTO.getEnginePower(), carTO.getMileage());
	}

	public static List<CarTO> map2TOs(List<CarEntity> entitiesList) {
		if(entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(CarMapper::toCarTO).collect(Collectors.toList());
	}

	public static List<CarEntity> map2entities(List<CarTO> carTOs) {
		if (carTOs == null) {
			return null;
		}
		return carTOs.stream().map(CarMapper::toCarEntity).collect(Collectors.toList());
	}

}
