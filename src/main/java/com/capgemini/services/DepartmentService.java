package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.DepartmentTO;

public interface DepartmentService {

	Long findDepartmentNo();
	
	DepartmentTO saveDepartment(DepartmentTO depTO);
	DepartmentTO updateDepartment(DepartmentTO depTO);
	DepartmentTO findDepartment(Long id);
	
	List<DepartmentTO> findAllDepartments();
	
	void removeDepartment(Long id);
	
}
