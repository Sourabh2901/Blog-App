package com.sr.Services.ServiceImpl;

import com.sr.Entities.Category;
import com.sr.Exception.ResourceNotFoundException;
import com.sr.Paylods.Dtos.CategoryDto;
import com.sr.Repository.CategoryRepo;
import com.sr.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto , Category.class);
        Category savedCategory = categoryRepo.save(category);
        return this.modelMapper.map(savedCategory , CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found With Category Id :"+ categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found With Category Id :"+ categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> category = categoryRepo.findAll();
        List<CategoryDto> categoryDto = category.stream()
                .map(cat -> this.modelMapper.map(cat , CategoryDto.class))
                .collect(Collectors.toList());

        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found With Category Id :"+ categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCategory = categoryRepo.save(category);

        return modelMapper.map(updateCategory, CategoryDto.class);
    }
}
