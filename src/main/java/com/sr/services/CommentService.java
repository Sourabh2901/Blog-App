package com.sr.services;

import com.sr.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto ,Integer postId);
	void deleteComment(Integer commentId);
	
}
