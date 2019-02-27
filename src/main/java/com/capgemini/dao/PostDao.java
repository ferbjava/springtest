package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.PostEntity;

@Repository
public interface PostDao extends CrudRepository<PostEntity, Long> {

}
