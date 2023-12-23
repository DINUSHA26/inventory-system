package com.flashmart.inventory.controller;

import com.flashmart.inventory.dto.CategoryProductResponse;
import com.flashmart.inventory.dto.CategoryRequest;
import com.flashmart.inventory.dto.CategoryResponse;
import com.flashmart.inventory.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/newCategory")
    @ResponseStatus(HttpStatus.CREATED)
    public String newCategory(@RequestBody CategoryRequest request){
        return categoryService.createNewCategory(request);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public List<CategoryResponse> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/productsByCategory")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryProductResponse> getAllProductsByCategory(){
        System.out.println("hehe");
        return categoryService.getAllCategoryProducts();
    }
}
