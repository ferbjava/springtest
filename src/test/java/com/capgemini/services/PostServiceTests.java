package com.capgemini.services;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entities.CommentEntity;
import com.capgemini.entities.PostEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class PostServiceTests {

	@Autowired
	private PostService postService;

	@Test
	@Transactional
	public void shouldFindLatestComment() {
		// given
		final String EXPECTED_REVIEW = "Rev02";
		PostEntity post01 = new PostEntity("Post 01");
		CommentEntity comm01 = new CommentEntity("Rev01", Timestamp.valueOf(LocalDateTime.of(2016, 2, 16, 16, 10, 21)));
		comm01.setPost(post01);
		CommentEntity comm02 = new CommentEntity(EXPECTED_REVIEW, Timestamp.valueOf(LocalDateTime.of(2017, 3, 16, 16, 10, 21)));
		comm02.setPost(post01);
		CommentEntity comm03 = new CommentEntity("Rev03", Timestamp.valueOf(LocalDateTime.of(2015, 4, 16, 16, 10, 21)));
		comm03.setPost(post01);
		post01.addComment(comm01);
		post01.addComment(comm02);
		post01.addComment(comm03);
		System.out.println("----Saving Post------");
		PostEntity savedPost01 = postService.savePost(post01);

		// when
		System.out.println("----Searching for Post------");
		PostEntity foundedPost01 = postService.findPost(savedPost01.getId());
		for (CommentEntity comment: foundedPost01.getCommnents()) {
			System.out.println("Comment: " + comment.getReview());
		}
		System.out.println("--------");
		System.out.println("Latest comment: " + foundedPost01.getLatestComment().getReview());

		// then
		assertEquals(EXPECTED_REVIEW, foundedPost01.getLatestComment().getReview());
	}

}
