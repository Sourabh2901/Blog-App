package com.sr.Services.ServiceImpl;

import com.sr.Entities.Comment;
import com.sr.Entities.Post;
import com.sr.Exception.ResourceNotFoundException;
import com.sr.Paylods.Dtos.CommentDto;
import com.sr.Repository.CommentRepo;
import com.sr.Repository.PostRepo;
import com.sr.Services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found With Id :"+postId));

        Comment comment = this.modelMapper.map(commentDto , Comment.class);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment ,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment Not Found with Id :"+commentId));
        commentRepo.delete(comment);
    }
}
