package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.capgemini.entities.CarEntity;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CarDao extends CrudRepository<CarEntity, Long> {

}
