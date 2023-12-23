package com.flashmart.inventory.service;

import com.flashmart.inventory.dto.CategoryProductResponse;
import com.flashmart.inventory.dto.CategoryRequest;
import com.flashmart.inventory.dto.CategoryResponse;
import com.flashmart.inventory.dto.ProductResponse;
import com.flashmart.inventory.model.Category;
import com.flashmart.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    public String createNewCategory(CategoryRequest request){
        Category category = Category.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .build();
        categoryRepository.save(category);
        return "Category "+ request.getName()+ " has been saved successfully";
    }

    public CategoryResponse getCategoryById(String id){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category!=null){
            return mapToCategoryResponse(category);
        }
        return null;
    }

    public List<CategoryResponse> getAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        System.out.println("All category");
        return categoryList.stream().map(this::mapToCategoryResponse).toList();
    }

    public List<CategoryProductResponse> getAllCategoryProducts(){
        List<CategoryResponse> categoryList = getAllCategories();
        System.out.println("categoryList");
        List<CategoryProductResponse> categoryProductResponseList = new ArrayList<>();

        for (CategoryResponse response:categoryList){
            List<ProductResponse> productResponseList = productService.getProductsByCategory(response.getCategoryId());
            CategoryProductResponse categoryProductResponse = CategoryProductResponse.builder()
                    .categoryId(response.getCategoryId())
                    .name(response.getName())
                    .icon(response.getIcon())
                    .productList(productResponseList)
                    .build();

            categoryProductResponseList.add(categoryProductResponse);
            System.out.println(categoryList);
        }
        return categoryProductResponseList;
    }

    private CategoryResponse mapToCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .icon(category.getIcon())
                .name(category.getName())
                .build();
    }
}
