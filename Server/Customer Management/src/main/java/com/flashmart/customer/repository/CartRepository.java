package com.flashmart.customer.repository;

import com.flashmart.customer.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findByUserId(String id);
    Cart findByProductIdAndUserId(String productId, String userId);
}
