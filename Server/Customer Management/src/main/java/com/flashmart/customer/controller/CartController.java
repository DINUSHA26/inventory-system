package com.flashmart.customer.controller;

import com.flashmart.customer.dto.CartRequest;
import com.flashmart.customer.dto.CartResponse;
import com.flashmart.customer.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getAllCart(){
        return cartService.getAllCartItems();
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartResponse> getAllCartByUserId(@PathVariable String id){
        return cartService.getAllCartsByUserId(id);
    }

    @PostMapping("/newItem")
    @ResponseStatus(HttpStatus.OK)
    public String addCartItem(@RequestBody CartRequest request){
        return cartService.addCartItem(request);
    }
}
