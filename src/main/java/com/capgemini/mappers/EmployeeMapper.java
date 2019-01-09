package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.EmployeeEntity;
import com.capgemini.tos.EmployeeTO;
import com.capgemini.tos.EmployeeTO.EmployeeTOBuilder;

public class EmployeeMapper {

	public static EmployeeTO toEmployeeTO(EmployeeEntity entity) {
		if (entity == null) {
			return null;
		}
		return new EmployeeTOBuilder().withId(entity.getId()).withFirstName(entity.getFirstName())
				.withLastName(entity.getLastName()).withDateBirth(entity.getDateBirth())
				.withPositionId(entity.getPosition().getId()).withDepartmentId(entity.getDepartment().getId())
				.withCarsIds(CarMapper.mat2TOids(entity.getCars())).build();
	}

	public static EmployeeEntity toEmployeeEntity(EmployeeTO employeeTO) {
		if (employeeTO == null) {
			return null;
		}
		return new EmployeeEntity(employeeTO.getId(), employeeTO.getFirstName(), employeeTO.getLastName(),
				employeeTO.getDateBirth());
	}

	public static List<EmployeeTO> map2TO(List<EmployeeEntity> entitiesList) {
		if (entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(EmployeeMapper::toEmployeeTO).collect(Collectors.toList());
	}

	public static List<EmployeeEntity> map2Entities(List<EmployeeTO> tosList) {
		if (tosList == null) {
			return null;
		}
		return tosList.stream().map(EmployeeMapper::toEmployeeEntity).collect(Collectors.toList());
	}

	public static List<Long> map2TOids(List<EmployeeEntity> entitiesList) {
		if (entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(EmployeeEntity::getId).collect(Collectors.toList());
	}

}
