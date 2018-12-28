package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.EmployeeEntity;

@Repository
public interface EmployeeDao extends CrudRepository<EmployeeEntity, Long> {

}
