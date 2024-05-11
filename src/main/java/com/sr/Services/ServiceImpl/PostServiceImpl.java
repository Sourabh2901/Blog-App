package com.sr.Services.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sr.Entities.Category;
import com.sr.Entities.Post;
import com.sr.Entities.User;
import com.sr.Exception.ResourceNotFoundException;
import com.sr.Paylods.Dtos.PostDto;
import com.sr.Paylods.PostResponse;
import com.sr.Repository.CategoryRepo;
import com.sr.Repository.PostRepo;
import com.sr.Repository.UserRepo;
import com.sr.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


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
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with id "+ userId));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() ->  new ResourceNotFoundException("Category Not Found with id "+ categoryId));

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
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found with id :"+postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postRepo.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found with id :"+postId));
        postRepo.deleteById(postId);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post Not Found with id :"+postId));
        return this.modelMapper.map(post , PostDto.class);
    }

//    @Override
//    public List<PostDto> getAllPost() {
//        List<Post> allPosts = postRepo.findAll();
//
//        List<PostDto> allPostDtos = allPosts.stream()
//                .map((post) -> this.modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
//
//        return allPostDtos;
//    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize , String sortBy , String sortOrder) {

        System.out.println("PageNumber ==="+pageNumber+" PageSize == "+pageSize);
        Sort sort = null;
        if(sortOrder.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }else {
            sort = Sort.by(sortBy).descending();
        }

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();

        List<PostDto> postDtos = allPosts.stream()
                        .map((post) -> modelMapper.map(post, PostDto.class))
                        .collect(Collectors.toList());

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
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category cat = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found with id :"+categoryId));
        List<Post> postByCategory = postRepo.findByCategory(cat);

        List<PostDto> postDtoByCategory = postByCategory.stream()
                .map((post) -> this.modelMapper.map(post , PostDto.class))
                .collect(Collectors.toList());

        return postDtoByCategory;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found with id :"+userId));

        List<Post> posts = postRepo.findByUser(user);

		List<PostDto> postDtoList = posts.stream()
                                    .map((post) -> modelMapper.map(post, PostDto.class))
                                    .collect(Collectors.toList());
        return postDtoList;
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