package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capgemini.services.PositionService;
import com.capgemini.tos.PositionTO;

@Controller
@ResponseBody
public class PositionController {

	@Autowired
	private PositionService positionService;

	@PostMapping(value = "/position", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PositionTO addNewPosition(@RequestBody PositionTO positionTO) {
		return positionService.savePosition(positionTO);
	}

	@GetMapping(value = "/position/{id}")
	public PositionTO showPositionDetails(@PathVariable("id") int id) {
		return positionService.findPosition(new Long(id));
	}
	
	@GetMapping(value = "/position")
	public List<PositionTO> showAllPositions() {
		return positionService.findAllPositions();
	}
	
	@PutMapping(value = "/position/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PositionTO updatePositionData(@RequestBody PositionTO positionTO) {
		return positionService.updatePosition(positionTO);
	}
	
	@DeleteMapping(value = "/position/{id}")
	public void removePosition(@PathVariable("id") int id) {
		positionService.removePosition(new Long(id));
	}

}
