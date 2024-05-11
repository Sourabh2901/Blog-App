package com.sr.Services;

import com.sr.Paylods.Dtos.PostDto;
import com.sr.Paylods.PostResponse;

import java.util.List;

public interface PostService {

    //	create
    PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);

    //	update
    PostDto updatePost(PostDto postDto ,Integer postId);

    //	delete
    void deletePost(Integer postId);

    //	getPostById
    PostDto getPostById(Integer postId);


    //	getAllPosts
    PostResponse getAllPost(Integer pageNumber , Integer pageSize , String sortBy , String sortOrder);
//    List<PostDto> getAllPost();


    //	get All Post By Category
    List<PostDto> getPostByCategory(Integer categoryId);

    //	get All Post By User
    List<PostDto> getPostByUser(Integer userId);

    //	search post
    List<PostDto> searchPosts(String keyword);
}
