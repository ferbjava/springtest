package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import com.capgemini.services.EmployeeService;
import com.capgemini.tos.EmployeeTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/employee", consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeTO addNewEmployee(@RequestBody EmployeeTO employeeTO) {
		return employeeService.saveEmployee(employeeTO);
	}

	@GetMapping(value = "/employee/{id}")
	public EmployeeTO showEmployeeDetails(@PathVariable("id") int id) {
		return employeeService.findEmployee(new Long(id));
	}

	@GetMapping(value = "/employee")
	public List<EmployeeTO> showAllEmployees() {
		return employeeService.findAllEmployees();
	}

	@PutMapping(value = "/employee/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeTO updateEmployeeData(@RequestBody EmployeeTO positionTO) {
		return employeeService.updateEmployee(positionTO);
	}

	@DeleteMapping(value = "/employee/{id}")
	public void removeEmployee(@PathVariable("id") int id) {
		employeeService.removeEmployee(new Long(id));
	}

}
