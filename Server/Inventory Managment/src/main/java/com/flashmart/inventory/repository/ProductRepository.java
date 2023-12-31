package com.flashmart.inventory.repository;

import com.flashmart.inventory.model.Category;
import com.flashmart.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByCategory(Category category);
}
