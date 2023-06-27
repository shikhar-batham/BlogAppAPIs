package com.crestdevs.BlogAppBE.service;

import com.crestdevs.BlogAppBE.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create category
    CategoryDto createCategory(CategoryDto categoryDto);

    //update category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //get category
    CategoryDto getCategory(Integer categoryId);

    //get all categories
    List<CategoryDto> getAllCategories();

    //delete category
    void deleteCategory(Integer categoryId);
}
