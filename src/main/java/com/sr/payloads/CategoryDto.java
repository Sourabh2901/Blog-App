package com.sr.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min =  4)
	private String categoryTitle;
	@NotBlank
	@Size(min = 10)
	private String categoryDescription;
	
}
