package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.PositionEntity;

@Repository
public interface PositionDao extends CrudRepository<PositionEntity, Long> {

}
