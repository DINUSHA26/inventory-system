package com.flashmart.order.repository;

import com.flashmart.order.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByStatus(int status);
}
