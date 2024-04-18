package com.sr.service;

import java.util.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sr.entities.Category;
import com.sr.entities.Post;
import com.sr.entities.User;
import com.sr.payloads.PostDto;
import com.sr.repositories.CategoryRepo;
import com.sr.repositories.PostRepo;
import com.sr.repositories.UserRepo;
import com.sr.services.impl.PostServiceImpl;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	
	@Mock
	private PostRepo postRepo;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private UserRepo userRepo;
	
	@Mock
	private CategoryRepo categoryRepo;
	
	@InjectMocks
	private PostServiceImpl postServiceImpl;
	
	
//	@BeforeAll
	public static void beforeAll() {
		System.out.println("Inside before All method");
//		MockitoAnnotations.openMocks(this);
	}
	
//	@BeforeEach
	public void setUp() {
		System.out.println("Inside before each method");
	}
	
//	@AfterEach
	public void afterEach() {
		System.out.println("Inside After each method");
	}
	
	@Test
	public void test_createPost() {
//		Arrange
		User user = User.builder().name("sourabh").email("sourabh@gmail.com").password("sourabh").about("Java Developer").build();
		Category category = Category.builder().categoryTitle("Backend").categoryDescription("Spring Framework").build();
		Post post = Post.builder().title("What is Java").content("Programming language").imageName("abc").date(new Date()).build();
		PostDto postDto = PostDto.builder().postId(post.getPostId()).title("What is Java").content("Programming Language").imageName("abc").date(new Date()).build();
		
		Mockito.when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
		Mockito.when(categoryRepo.findById(user.getId())).thenReturn(Optional.of(category));
		Mockito.when(modelMapper.map(postDto, Post.class)).thenReturn(post);
		Mockito.when(modelMapper.map(post, PostDto.class)).thenReturn(postDto);
		Mockito.when(postRepo.save(Mockito.any(Post.class))).thenReturn(post);
		
		
//		Act
		PostDto createdPost = postServiceImpl.createPost(postDto, user.getId(), category.getCategoryId());
		
//		Assert
        Assertions.assertThat(createdPost).isNotNull();
        Assertions.assertThat(createdPost.getTitle()).isEqualTo(post.getContent());
		
	}
	
//	@Test
	public void test2() {
		System.out.println("Inside test2 method");
	}
	
	
//	@AfterAll
	public static void afterAll() {
		System.out.println("Inside After All method");
	}
}
