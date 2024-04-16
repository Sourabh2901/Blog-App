package com.sr.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sr.entities.Category;
import com.sr.entities.Post;
import com.sr.entities.User;
import com.sr.execeptions.ResourceNotFoundException;
import com.sr.payloads.PostDto;
import com.sr.payloads.PostResponse;
import com.sr.repositories.CategoryRepo;
import com.sr.repositories.PostRepo;
import com.sr.repositories.UserRepo;
import com.sr.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired 
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		Post post = modelMapper.map(postDto ,Post.class);
		post.setImageName("Default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost = postRepo.save(post);
		
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.deleteById(postId);
	}
	
	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize ,String sortBy ,String sortOrder) {
		
		Sort sort = null;
		if(sortOrder.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
	
		List<PostDto> postDtos = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		List<Post> posts = postRepo.findByCategory(category);
		
		List<PostDto> list = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
//		List<PostDto> list = new ArrayList<>();
//		for(Post obj : posts) {
//			list.add(modelMapper.map(obj, PostDto.class));
//		}
//		
		return list;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		List<Post> posts = postRepo.findByUser(user);	
		
//		List<PostDto> postDtoList = posts.stream().map((posts) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		List<PostDto> list = new ArrayList<>();
		for(Post obj : posts) {
			list.add(modelMapper.map(obj, PostDto.class));
		}
		
		return list;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List <Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream()
								.map((post) -> modelMapper.map(post, PostDto.class))
								.collect(Collectors.toList());
		
		return postDtos;
	}





}
