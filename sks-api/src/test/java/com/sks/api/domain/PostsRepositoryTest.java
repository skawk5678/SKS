package com.sks.api.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PostsRepositoryTest {

	@Autowired
	private PostsRepository postsRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void createPosts() {

		Posts posts = new Posts();
		posts.setAuthor("yoonsung");
		posts.setContent("hi");
		posts.setTitle("sks");

		postsRepository.save(posts);
	}

	@Test
	public void deletePosts() {
		postsRepository.deleteById(1);
	}

	@Test
	public void countPosts() {
		log.info("postsCount: {}",postsRepository.count());
	}

	@Test
	public void findAllPosts() {
		log.info("postsInfo: {}",postsRepository.findAll());
	}
}
