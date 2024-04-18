package com.sr.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

	private Integer postId;
	private String title;	
	private String content;
	private String imageName;
	private Date date;
	
	private CategoryDto category;	
	private UserDto user;
	private Set<CommentDto> comments = new HashSet<>();
	
}
