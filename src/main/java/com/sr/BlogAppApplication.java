package com.sr;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sr.config.AppConstant;
import com.sr.entities.Role;
import com.sr.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		try {
			Role role1 = new Role();
			role1.setId(AppConstant.ADMIN_USER);
			role1.setName("ADMIN");
			
			Role role2 = new Role();
			role2.setId(AppConstant.NORMAL_USER);
			role2.setName("USER");
			
			List<Role> roles = List.of(role1 ,role2);
			
			List<Role> saved = roleRepo.saveAll(roles);
			
			saved.forEach((role) -> System.out.println(role.getName()));
			
		}catch (Exception e) {
			throw new Exception("Error Occured while creating Roles");
		}
		
	}

}
