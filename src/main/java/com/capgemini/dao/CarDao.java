package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.CarEntity;

@Repository
public interface CarDao extends CrudRepository<CarEntity, Long> {

}
