package com.capgemini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.services.DepartmentService;
import com.capgemini.tos.DepartmentTO;

@Controller
@ResponseBody
public class DepartmentController {

	@Autowired
	private DepartmentService depService;
	
	@PostMapping(value = "/dep", consumes = MediaType.APPLICATION_JSON_VALUE)
	public DepartmentTO addNewDepartment(@RequestBody DepartmentTO depTO) {
		return depService.saveDepartment(depTO);
	}
	
}
