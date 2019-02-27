package com.capgemini.services;

import com.capgemini.entities.PostEntity;

public interface PostService {

	PostEntity savePost(PostEntity postEntity);
	PostEntity findPost(Long id);

}
