package com.capgemini.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entities.CommentEntity;

@Repository
public interface CommentDao extends CrudRepository<CommentEntity, Long> {

}
