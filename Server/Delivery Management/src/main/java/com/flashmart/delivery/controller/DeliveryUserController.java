package com.flashmart.delivery.controller;

import com.flashmart.delivery.dto.UserRequest;
import com.flashmart.delivery.dto.UserResponse;
import com.flashmart.delivery.model.User;
import com.flashmart.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery/user")
@RequiredArgsConstructor
public class DeliveryUserController {

    private final UserService userService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String newDeliveryUser(@PathVariable String id){
        return userService.createDeliveryUser(id);
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getAvailableUser(){
        return userService.getAvailableUser();
    }


}
