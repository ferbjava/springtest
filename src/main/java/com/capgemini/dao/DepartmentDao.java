package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.capgemini.entities.DepartmentEntity;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface DepartmentDao extends CrudRepository<DepartmentEntity, Long> {

}
