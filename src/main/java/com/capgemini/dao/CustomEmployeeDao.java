package com.capgemini.dao;

import java.util.List;

import com.capgemini.entities.EmployeeEntity;

public interface CustomEmployeeDao {
	
	List<EmployeeEntity> findEmployeesByPositionId(Long positionId);

}
