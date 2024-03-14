package com.sr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.payloads.ApiResponse;
import com.sr.payloads.CommentDto;
import com.sr.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto ,@PathVariable Integer postId){
		CommentDto createdComment = commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createdComment ,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse("Comment Deleted" ,true) ,HttpStatus.OK);
	}
	
}
