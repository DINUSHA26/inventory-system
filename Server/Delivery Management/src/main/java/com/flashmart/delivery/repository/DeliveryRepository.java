package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Delivery findByOrderId(String orderId);
}
