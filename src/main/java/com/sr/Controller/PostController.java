package com.sr.Controller;

import com.sr.Constants.AppConstant;
import com.sr.Paylods.ApiResponse;
import com.sr.Paylods.Dtos.PostDto;
import com.sr.Paylods.PostResponse;
import com.sr.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

//    @Value("${project.image}")
//    private String path;

    //	CREATE
    @PostMapping("/users/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
                                              @PathVariable Integer userId ,
                                              @PathVariable Integer categoryId){

        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost , HttpStatus.CREATED);
    }

    //	Get Post By User id
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> getPosts = postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(getPosts ,HttpStatus.OK);
    }

    //	Get Post By Category id
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> getPosts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(getPosts ,HttpStatus.OK);
    }

    //	Get Post By PostId
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto post = postService.getPostById(postId);
        return new ResponseEntity<>(post ,HttpStatus.OK);
    }

    //	Get All Posts
//    @GetMapping("/posts")
//    public ResponseEntity<List<PostDto>> getAllPosts(){
//        List<PostDto> postDtoList = postService.getAllPost();
//        return new ResponseEntity<>(postDtoList ,HttpStatus.OK);
//    }

    //	Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber" ,defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber ,
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

//    Get Post with the mentioned Keywords
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> result = postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result ,HttpStatus.OK);
    }


}
