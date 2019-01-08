package com.capgemini.mappers;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.entities.DepartmentEntity;
import com.capgemini.tos.DepartmentTO;

public class DepartmentMapper {

	public static DepartmentTO toDepartmentTO(DepartmentEntity entity) {
		if (entity == null) {
			return null;
		}
		return new DepartmentTO.DepartmentTOBuilder().withId(entity.getId()).withDepAdress(entity.getDepAdrees())
				.withDepTelephone(entity.getDepTelephone())
				.withEmployeesIds(EmployeeMapper.map2TOids(entity.getEmployees())).build();
	}

	public static DepartmentEntity toDepartmentEntity(DepartmentTO depTO) {
		if (depTO == null) {
			return null;
		}
		return new DepartmentEntity(depTO.getId(), depTO.getDepAdress(), depTO.getDepTelephone());
	}

	public static List<DepartmentTO> map2TO(List<DepartmentEntity> entitiesList) {
		if (entitiesList == null) {
			return null;
		}
		return entitiesList.stream().map(DepartmentMapper::toDepartmentTO).collect(Collectors.toList());
	}

	public static List<DepartmentEntity> map2Entities(List<DepartmentTO> tosList) {
		if (tosList == null) {
			return null;
		}
		return tosList.stream().map(DepartmentMapper::toDepartmentEntity).collect(Collectors.toList());
	}
}
