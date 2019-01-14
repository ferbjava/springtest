package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.entities.EmployeeEntity;
import com.capgemini.entities.PositionEntity;
import com.capgemini.mappers.PositionMapper;
import com.capgemini.services.PositionService;
import com.capgemini.tos.PositionTO;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private EmployeeDao empDao;

	@Override
	public Long findPositionNo() {
		return positionDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public PositionTO savePosition(PositionTO positionTO) {
		PositionEntity entity = PositionMapper.toPositionEntity(positionTO);
		return PositionMapper.toPositionTO(positionDao.save(entity));
	}

	@Override
	public PositionTO findPosition(Long id) {
		return PositionMapper.toPositionTO(positionDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public PositionTO updatePosition(PositionTO positionTO) {
		PositionEntity entity = PositionMapper.toPositionEntity(positionTO);
		positionTO.getEmployeesId().forEach(empId -> {
			EmployeeEntity empEnt = empDao.findOne(empId);
			empEnt.setPosition(entity);
			entity.addEmployee(empEnt);
		});
		return PositionMapper.toPositionTO(positionDao.save(entity));
	}

	@Override
	public List<PositionTO> findAllPositions() {
		List<PositionEntity> positionsList = new ArrayList<>();
		positionDao.findAll().forEach(positionsList::add);
		return PositionMapper.map2TO(positionsList);
	}

	@Override
	@Transactional(readOnly = false)
	public void removePosition(Long id) {
		List<EmployeeEntity> employees = empDao.findEmployeesByPositionId(id);
		for(EmployeeEntity e : employees) {
			empDao.delete(e.getId());
		}
		positionDao.delete(id);
	}

}
