package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.PositionDao;
import com.capgemini.entities.DepartmentEntity;
import com.capgemini.entities.EmployeeEntity;
import com.capgemini.entities.PositionEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.services.EmployeeService;
import com.capgemini.tos.EmployeeTO;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private PositionDao posDao;

	@Autowired
	private DepartmentDao depDao;

	@Override
	public Long findEmployeeNo() {
		return employeeDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeTO saveEmployee(EmployeeTO employeeTO) {
		EmployeeEntity entity = EmployeeMapper.toEmployeeEntity(employeeTO);
		PositionEntity posEnt = posDao.findOne(employeeTO.getPositionId());
		DepartmentEntity depEnt = depDao.findOne(employeeTO.getDepartmentId());
		entity.setPosition(posEnt);
		entity.setDepartment(depEnt);
		EmployeeEntity savedEmployee = employeeDao.save(entity);
		return EmployeeMapper.toEmployeeTO(savedEmployee);
	}

	@Override
	public EmployeeTO findEmployee(Long id) {
		return EmployeeMapper.toEmployeeTO(employeeDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeTO updateEmployee(EmployeeTO employeeTO) {
		EmployeeEntity entity = EmployeeMapper.toEmployeeEntity(employeeTO);
		entity.setPosition(posDao.findOne(employeeTO.getPositionId()));
		entity.setDepartment(depDao.findOne(employeeTO.getDepartmentId()));
		return EmployeeMapper.toEmployeeTO(employeeDao.save(entity));
	}

	@Override
	public List<EmployeeTO> findAllEmployees() {
		List<EmployeeEntity> employeesList = new ArrayList<>();
		employeeDao.findAll().forEach(employeesList::add);
		return EmployeeMapper.map2TO(employeesList);
	}

	@Override
	@Transactional(readOnly = false)
	public void removeEmployee(Long id) {
		employeeDao.delete(id);
	}

	@Override
	public List<EmployeeTO> findEmployeesByPositionId(Long positionId) {
		return EmployeeMapper.map2TO(employeeDao.findEmployeesByPositionId(positionId));
	}

}
