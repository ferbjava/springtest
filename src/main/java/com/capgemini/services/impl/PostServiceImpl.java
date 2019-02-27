package com.capgemini.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.PostDao;
import com.capgemini.entities.PostEntity;
import com.capgemini.services.PostService;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao postDao;


	@Override
	@Transactional(readOnly = false)
	public PostEntity savePost(PostEntity postEntity) {
		return postDao.save(postEntity);
	}

	@Override
	@Transactional(readOnly = false)
	public PostEntity findPost(Long id) {
		System.out.println("---Finding started---");
		PostEntity entity = postDao.findOne(id);
		System.out.println("---Finding ended---");
		return entity;
	}

}
