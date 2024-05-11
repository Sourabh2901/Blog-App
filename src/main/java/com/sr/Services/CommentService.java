package com.sr.Services;

import com.sr.Paylods.Dtos.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto ,Integer postId);
    void deleteComment(Integer commentId);
}
