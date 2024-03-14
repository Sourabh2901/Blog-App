package com.sr.services;

import java.util.List;

import com.sr.payloads.CategoryDto;

public interface CategoryService {
	
//	 create
	 CategoryDto createCategory(CategoryDto categoryDto);
	 
//	 delete
	 void deleteCategory(Integer categoryId);
	 
//	 get
	 CategoryDto getCategory(Integer categoryId);
	 
//	 getAll
	 List<CategoryDto> getAllCategory();
	 
//	 update
	 CategoryDto updateCategory(CategoryDto categoryDto ,Integer categoryId);
}
