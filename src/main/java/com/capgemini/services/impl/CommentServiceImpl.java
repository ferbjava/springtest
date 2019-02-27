package com.capgemini.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.CommentDao;
import com.capgemini.entities.CommentEntity;
import com.capgemini.services.CommentService;

@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentEntity saveComment(CommentEntity commentEntity) {
		return commentDao.save(commentEntity);
	}

	@Override
	public CommentEntity findComment(Long id) {
		return commentDao.findOne(id);
	}

}
