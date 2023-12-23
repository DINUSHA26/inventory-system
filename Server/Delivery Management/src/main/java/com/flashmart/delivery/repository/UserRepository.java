package com.flashmart.delivery.repository;

import com.flashmart.delivery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByStatus(int available);
}
