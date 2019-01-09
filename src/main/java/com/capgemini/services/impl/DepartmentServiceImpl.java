package com.capgemini.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.entities.DepartmentEntity;
import com.capgemini.entities.EmployeeEntity;
import com.capgemini.mappers.DepartmentMapper;
import com.capgemini.services.DepartmentService;
import com.capgemini.tos.DepartmentTO;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	private DepartmentDao depDao;

	@Autowired
	private EmployeeDao empDao;

	@Override
	@Transactional(readOnly = false)
	public DepartmentTO saveDepartment(DepartmentTO depTO) {
		DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(depTO);
		return DepartmentMapper.toDepartmentTO(depDao.save(entity));
	}

	@Override
	public DepartmentTO findDepartment(Long id) {
		return DepartmentMapper.toDepartmentTO(depDao.findOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public DepartmentTO updateDepartment(DepartmentTO depTO) {
		DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(depTO);
		depTO.getEmployeesIds().forEach(empId -> {
			entity.addEmployee(empDao.findOne(empId));
		});
		return DepartmentMapper.toDepartmentTO(depDao.save(entity));
	}

	@Override
	public List<DepartmentTO> findAllDepartmetns() {
		List<DepartmentEntity> depList = new ArrayList<>();
		depDao.findAll().forEach(depList::add);
		return DepartmentMapper.map2TO(depList);
	}

	@Override
	public Long findDepartmentNo() {
		return depDao.count();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeDepartment(Long id) {
		List<EmployeeEntity> employees = empDao.findEmployeesByDepartmentId(id);
		for(EmployeeEntity e: employees) {
			empDao.delete(e.getId());
		}
		depDao.delete(id);
	}

}
