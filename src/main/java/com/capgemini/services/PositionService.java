package com.capgemini.services;

import java.util.List;

import com.capgemini.tos.PositionTO;

public interface PositionService {
	
	Long findPositionNo();

	PositionTO savePosition(PositionTO positionTO);

	PositionTO findPosition(Long id);

	PositionTO updatePosition(PositionTO positionTO);
	
	List<PositionTO>findAllCPositions();

	void removePosition(Long id);

}
