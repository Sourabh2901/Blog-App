package com.sr.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sr.config.AppConstant;
import com.sr.payloads.ApiResponse;
import com.sr.payloads.PostDto;
import com.sr.payloads.PostResponse;
import com.sr.services.FileService;
import com.sr.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

//	CREATE
	@PostMapping("/users/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			@PathVariable Integer userId ,@PathVariable Integer categoryId){
		
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost ,HttpStatus.CREATED);
	}
	
//	Get Post By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> getPosts = postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(getPosts ,HttpStatus.OK);
	}
	
//	Get Post By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> getPosts = postService.getPostByUser(userId);
		return new ResponseEntity<>(getPosts ,HttpStatus.OK);
	}
	
//	Get Post By Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto post = postService.getPostById(postId);
		return new ResponseEntity<>(post ,HttpStatus.OK);
	}
	
	
//	Get All Posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber" ,defaultValue = AppConstant.PAGE_NUMBER ,required = false) Integer pageNumber ,
			@RequestParam(value ="pageSize" ,defaultValue = AppConstant.PAGE_SIZE ,required = false) Integer pageSize ,
			@RequestParam(value="sortBy" ,defaultValue = AppConstant.SORT_BY ,required = false) String sortBy ,
			@RequestParam(value="sortOrder" ,defaultValue = AppConstant.SORT_ORDER ,required = false) String sortOrder){
		
		PostResponse postResponse = postService.getAllPost(pageNumber ,pageSize ,sortBy ,sortOrder);
		return new ResponseEntity<>(postResponse ,HttpStatus.OK);
	}
	
//	Delete Posts
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		
		postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post Deleted Succesfully", true) ,HttpStatus.OK);
	}
	
//	Update Posts
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable Integer postId){
		
		PostDto updatedPost =  postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatedPost ,HttpStatus.OK);
	}


	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> result = postService.searchPosts(keywords);
		
		return new ResponseEntity<List<PostDto>>(result ,HttpStatus.OK);
	}
	
	
	
	@PostMapping("post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image ,
			@PathVariable Integer postId) throws IOException{
		
			PostDto postDto = postService.getPostById(postId);	
			
			String fileName = fileService.uploadImage(path, image);
			postDto.setImageName(fileName);
			PostDto updatedPost = postService.updatePost(postDto, postId);
		
		
			return new ResponseEntity<>(updatedPost ,HttpStatus.OK);
	}
	
	
//	Method to Serve File
	@GetMapping(value = "/images/{imageName}" ,produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName ,HttpServletResponse response) throws IOException {
		
		InputStream resource = null;
		try {
			resource = fileService.getResource(path, imageName);
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			StreamUtils.copy(resource, response.getOutputStream());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
