package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.EmployeeTO;

public interface EmployeeService {
	
	Long findEmployeeNo();

	EmployeeTO saveEmployee(EmployeeTO employeeTO);

	EmployeeTO findEmployee(Long id);

	EmployeeTO updateEmployee(EmployeeTO employeeTO);
	
	List<EmployeeTO>findAllEmployees();

	void removeEmployee(Long id);

}
