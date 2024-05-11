package com.sr.Services;

import com.sr.Paylods.Dtos.CategoryDto;

import java.util.List;

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
