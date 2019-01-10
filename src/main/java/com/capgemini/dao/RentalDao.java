package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.RentalEntity;

@Repository
public interface RentalDao extends CrudRepository<RentalEntity, Long> {

}
