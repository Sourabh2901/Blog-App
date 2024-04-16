package com.sr.RepoTest;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.sr.entities.User;
import com.sr.repositories.UserRepo;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class userRepo {
	
	@Autowired
	private UserRepo userRepo;
	
	
//	@BeforeAll
//	public void init() {
////		Arrange
//		Role role1 = Role.builder().id(AppConstant.ADMIN_USER).name("ADMIN").build();
//		Role role2 = Role.builder().id(AppConstant.NORMAL_USER).name("USER").build();
//		List<Role> roles = List.of(role1 ,role2);
//		
////		Act
//		List<Role> savedRoles = roleRepo.saveAll(roles);
//		
////		Assert
//		Assertions.assertThat(savedRoles).isNotNull();
////		Assertions.assertThat(savedRoles.get(0).getName()).isEqualTo(role1.getName());
//		
//	}
	
	@Test
	public void test_saveUser() {
//		Arange
		User user = User.builder().name("Sourabh").about("Java Developer").email("sourabh@gmail.com").password("sourabh").build();
		
//		Act
		User savedUser = userRepo.save(user);
		
//		Assert
		Assertions.assertThat(savedUser).isNotNull();
		Assertions.assertThat(savedUser.getName()).isEqualTo(user.getName());
	}
	
	@Test
	public void test_getUser() {
//		Arange
		User user = User.builder().name("Sourabh").about("Java Developer").email("sourabh@gmail.com").password("sourabh").build();
		
//		Act
		User savedUser = userRepo.save(user);
		Optional<User> getUser = userRepo.findById(savedUser.getId());
		
//		Assert
		Assertions.assertThat(getUser.get()).isNotNull();
		Assertions.assertThat(getUser.get().getName()).isEqualTo(user.getName());
	}
	
	@Test
	public void test_getAllUser() {
//		Arange
		User user1 = User.builder().name("Sourabh").about("Java Developer").email("sourabh@gmail.com").password("sourabh").build();
		User user2 = User.builder().name("Sr").about("Java Developer").email("sr@gmail.com").password("sr").build();
//		Act
		userRepo.saveAll(List.of(user1,user2));
		List<User> getUser = userRepo.findAll();
		
//		Assert
		Assertions.assertThat(getUser).isNotNull();
		Assertions.assertThat(getUser.get(0).getName()).isEqualTo(user1.getName());
	}
	
	
	@Test
	public void test_updateUser() {
//		Arange
		User user1 = User.builder().name("Sourabh").about("Java Developer").email("sourabh@gmail.com").password("sourabh").build();

//		Act
		User savedUser = userRepo.save(user1);
		User user = userRepo.findById(savedUser.getId()).orElseThrow(() -> new RuntimeException());
		user.setName("Sourabh Rai");
		User updateUser = userRepo.save(user);
		
//		Assert
		Assertions.assertThat(updateUser).isNotNull();
		Assertions.assertThat(updateUser.getName()).isEqualTo(user.getName());
	}
	
	
	@Test
	public void test_deleteUser() {
//		Arange
		User user1 = User.builder().name("Sourabh").about("Java Developer").email("sourabh@gmail.com").password("sourabh").build();

//		Act
		User savedUser = userRepo.save(user1);
		userRepo.deleteById(savedUser.getId());
		Optional<User> deletedUser = userRepo.findById(savedUser.getId());
		
		
//		Assert
		Assertions.assertThat(deletedUser).isEmpty();
	}	

}
