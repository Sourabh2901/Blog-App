package com.sr.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.entities.Category;
import com.sr.execeptions.ResourceNotFoundException;
import com.sr.payloads.CategoryDto;
import com.sr.repositories.CategoryRepo;
import com.sr.services.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepo.save(category);
		return this.modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> category = categoryRepo.findAll();
		List<CategoryDto> categoryDto = new ArrayList<>();
		
		for(Category cat : category) {
			categoryDto.add(modelMapper.map(cat, CategoryDto.class));
		}
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updateCategory = categoryRepo.save(category);
		
		return modelMapper.map(updateCategory, CategoryDto.class);
	}
	

}
