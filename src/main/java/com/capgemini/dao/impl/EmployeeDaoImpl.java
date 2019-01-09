package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.capgemini.dao.CustomEmployeeDao;
import com.capgemini.entities.EmployeeEntity;

public class EmployeeDaoImpl implements CustomEmployeeDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public List<EmployeeEntity> findEmployeesByPositionId(Long positionId) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"SELECT e FROM EmployeeEntity e, PositionEntity p WHERE e MEMBER OF p.employees AND p.id = :id",
				EmployeeEntity.class);
		return query.setParameter("id", positionId).getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeesByDepartmentId(Long depId) {
		TypedQuery<EmployeeEntity> query = entityManager.createQuery(
				"SELECT e FROM EmployeeEntity e, DepartmentEntity d WHERE e MEMBER OF d.employees AND d.id = :id",
				EmployeeEntity.class);
		return query.setParameter("id", depId).getResultList();
	}

}
