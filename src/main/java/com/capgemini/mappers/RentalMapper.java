package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.RentalEntity;
import com.capgemini.tos.RentalTO;
import com.capgemini.tos.RentalTO.RentalTOBuilder;

public class RentalMapper {

	public static RentalTO toRentalTO(RentalEntity entity) {
		if (entity == null) {
			return null;
		}
		return new RentalTOBuilder().withId(entity.getId()).withRentalDate(entity.getRentalDate())
				.withClientId(entity.getClient().getId()).build();
	}

	public static RentalEntity toRentalEntity(RentalTO rentalTO) {
		if (rentalTO == null) {
			return null;
		}
		return new RentalEntity(rentalTO.getId(), rentalTO.getRentalDate());
	}

	public static List<RentalTO> map2TO(List<RentalEntity> entityList) {
		if (entityList == null) {
			return null;
		}
		return entityList.stream().map(RentalMapper::toRentalTO).collect(Collectors.toList());
	}

	public static List<RentalEntity> map2Entities(List<RentalTO> tosList) {
		if (tosList == null) {
			return null;
		}
		return tosList.stream().map(RentalMapper::toRentalEntity).collect(Collectors.toList());
	}
	
	public static List<Long> matoTOids(List<RentalEntity> entityList) {
		if (entityList == null) {
			return null;
		}
		return entityList.stream().map(RentalEntity::getId).collect(Collectors.toList());
	}

}
