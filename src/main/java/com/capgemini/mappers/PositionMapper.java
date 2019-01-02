package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.PositionEntity;
import com.capgemini.tos.PositionTO;
import com.capgemini.tos.PositionTO.PositionTOBuilder;

public class PositionMapper {

	public static PositionTO toPositionTO(PositionEntity entity) {
		if (entity == null) {
			return null;
		}
		return new PositionTOBuilder().withId(entity.getId()).withPositionName(entity.getPositionName())
				.withEmployeesId(EmployeeMapper.map2TOids(entity.getEmployees())).build();
	}

	public static PositionEntity toPositionEntity(PositionTO positionTO) {
		if (positionTO == null) {
			return null;
		}
		return new PositionEntity(positionTO.getId(), positionTO.getPositionName());
	}

	public static List<PositionTO> map2TO(List<PositionEntity> entitiesList) {
		if (entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(PositionMapper::toPositionTO).collect(Collectors.toList());
	}

	public static List<PositionEntity> map2Entities(List<PositionTO> tosList) {
		if (tosList == null) {
			return null;
		}
		return tosList.stream().map(PositionMapper::toPositionEntity).collect(Collectors.toList());
	}

	public static List<Long> map2TOids(List<PositionEntity> entitiesList) {
		if (entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(PositionMapper::toPositionTO).map(PositionTO::getId)
				.collect(Collectors.toList());
	}

}
