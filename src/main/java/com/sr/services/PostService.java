package com.sr.services;

import java.util.List;

import com.sr.payloads.PostDto;
import com.sr.payloads.PostResponse;

public interface PostService {
	
//	create
	PostDto createPost(PostDto postDto ,Integer userId ,Integer categoryId);
	
//	update
	PostDto updatePost(PostDto postDto ,Integer postId);
	
//	delete
	void deletePost(Integer postId);
	
//	getAllPosts
	PostResponse getAllPost(Integer pageNumber ,Integer pageSize ,String sortBy ,String sortOrder);
	
//	getPostById
	PostDto getPostById(Integer postId);
	
//	get All Post By Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
//	get All Post By User
	List<PostDto> getPostByUser(Integer userId);
	
//	search post
	List<PostDto> searchPosts(String keyword);

}
