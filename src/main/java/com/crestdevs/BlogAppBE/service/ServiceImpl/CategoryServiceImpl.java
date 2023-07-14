package com.crestdevs.BlogAppBE.service.ServiceImpl;

import com.crestdevs.BlogAppBE.entity.Category;
import com.crestdevs.BlogAppBE.exception.ResourceNotFoundException;
import com.crestdevs.BlogAppBE.payload.CategoryDto;
import com.crestdevs.BlogAppBE.repository.CategoryRepo;
import com.crestdevs.BlogAppBE.service.CategoryService;
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

        Category category = this.modelMapper.map(categoryDto, Category.class);

        Category savedCategory = this.categoryRepo.save(category);

        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category fetchedCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "category_id", categoryId));

        fetchedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        fetchedCategory.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCategory = this.categoryRepo.save(fetchedCategory);

        return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category fetchedCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "category_id", categoryId));

        return this.modelMapper.map(fetchedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        List<Category>fetchedCategoryList=this.categoryRepo.findAll();

       return fetchedCategoryList.stream().map(category -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category fetchedCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category", "category_id", categoryId));

        this.categoryRepo.delete(fetchedCategory);
    }
}
