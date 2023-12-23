package com.flashmart.inventory.repository;

import com.flashmart.inventory.model.ProductOnHold;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductOnHoldRepository extends JpaRepository<ProductOnHold, String> {
    List<ProductOnHold> findByHoldTimeBefore(Date cutoffTime);
}
