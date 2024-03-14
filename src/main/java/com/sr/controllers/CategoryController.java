package com.sr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sr.payloads.ApiResponse;
import com.sr.payloads.CategoryDto;
import com.sr.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdCategory =  categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{categoryId}")
	private ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable Integer categoryId) {
		CategoryDto updatedCategory =  categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully" ,true), HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	private ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
		CategoryDto getCategory =  categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);
	}
	
	@GetMapping("/")
	private ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> getAllCategory =  categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(getAllCategory, HttpStatus.OK);
	}

}
