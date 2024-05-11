package com.sr.Controllers;

//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import java.util.Arrays;
//import java.util.Set;
//
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.sr.controllers.UserController;
//import com.sr.payloads.RoleDto;
//import com.sr.payloads.UserDto;
//import com.sr.services.UserService;
//
//@WebMvcTest(controllers = UserController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private UserService userService;
//
//	private UserDto userDto;
//	private RoleDto roleDto;
//
//	@BeforeEach
//	public void init() {
////		User user = User.builder().name("Sourabh").about("Java Developer")
////				.email("sourabh@gmail.com").password("sourabh").build();
//
//		userDto = UserDto.builder().name("Sourabh").about("Java Developer")
//				.email("sourabh@gmail.com").password("sourabh").build();
//
////		roleDto = RoleDto.builder().name("NORMAL_USER").id(1).build();
//		userDto.setRoles(Set.of(roleDto));
//	}
//
//	@Test
//	public void test_getAllUsers() throws Exception {
//		when(userService.getAllUsers()).thenReturn(Arrays.asList(userDto));
//
//		ResultActions response = mockMvc.perform(get("/api/users")
//										.contentType(MediaType.APPLICATION_JSON));
//
//		response.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.about", CoreMatchers.is(userDto.getAbout())));
//	}
//
//}
