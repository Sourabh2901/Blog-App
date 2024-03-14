package com.sr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sr.entities.Category;
import com.sr.entities.Post;
import com.sr.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);
	
}
