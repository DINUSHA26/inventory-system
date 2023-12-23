package com.flashmart.inventory.repository;

import com.flashmart.inventory.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
