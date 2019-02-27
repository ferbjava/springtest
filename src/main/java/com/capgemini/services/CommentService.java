package com.capgemini.services;

import com.capgemini.entities.CommentEntity;

public interface CommentService {

	CommentEntity saveComment(CommentEntity commentEntity);
	CommentEntity findComment(Long id);

}
