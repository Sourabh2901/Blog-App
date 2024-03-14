package com.sr.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "user_name" ,nullable = false ,length = 100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
	private List<Post> posts = new ArrayList<>();
	
//	@OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
//	private Set<Comment> comments = new HashSet<>();

}
