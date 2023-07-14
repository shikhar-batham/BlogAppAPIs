package com.crestdevs.BlogAppBE.controller;

import com.crestdevs.BlogAppBE.payload.ApiResponse;
import com.crestdevs.BlogAppBE.payload.CategoryDto;
import com.crestdevs.BlogAppBE.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {

        CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,
                                                      @RequestParam("categoryId") Integer categoryId) {

        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>(new ApiResponse("category was deleted!", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public CategoryDto getCategory(@RequestParam Integer categoryId) {

        CategoryDto categoryDto = this.categoryService.getCategory(categoryId);

        return categoryDto;
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {

        List<CategoryDto> categoryDtoList = this.categoryService.getAllCategories();

        return new ResponseEntity<>(categoryDtoList, HttpStatus.FOUND);
    }

}
