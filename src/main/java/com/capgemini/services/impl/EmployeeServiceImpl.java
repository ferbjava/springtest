package com.capgemini.services.impl;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.entities.EmployeeEntity;
import com.capgemini.mappers.EmployeeMapper;
import com.capgemini.services.EmployeeService;
import com.capgemini.tos.EmployeeTO;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	public Long findEmployeeNo() {
		return employeeDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeTO saveEmployee(EmployeeTO employeeTO) {
		EmployeeEntity entity = EmployeeMapper.toEmployeeEntity(employeeTO);
		return EmployeeMapper.toEmployeeTO(employeeDao.save(entity));
	}

	@Override
	public EmployeeTO findEmployee(Long id) {
		return EmployeeMapper.toEmployeeTO(employeeDao.findById(id).get());
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeTO updateEmployee(EmployeeTO employeeTO) {
		EmployeeEntity entity = EmployeeMapper.toEmployeeEntity(employeeTO);
		return EmployeeMapper.toEmployeeTO(employeeDao.save(entity));
	}

	@Override
	public List<EmployeeTO> findAllEmployees() {
		return EmployeeMapper.map2TO(Lists.newArrayList(employeeDao.findAll()));
	}

	@Override
	@Transactional(readOnly = false)
	public void removeEmployee(Long id) {
		employeeDao.deleteById(id);
	}

}
