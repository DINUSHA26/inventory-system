package com.flashmart.customer.service;

import com.flashmart.customer.config.WebClientConfig;
import com.flashmart.customer.dto.CartRequest;
import com.flashmart.customer.dto.CartResponse;
import com.flashmart.customer.model.Cart;
import com.flashmart.customer.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final WebClientConfig webClient;
    private final CartRepository cartRepository;

    public List<CartResponse> getAllCartItems(){
        List<Cart> cartList = cartRepository.findAll();
        return cartList.stream().map(this::mapTocartResponse).toList();
    }

    public String addCartItem(CartRequest request){
        Cart cart = cartRepository.findByProductIdAndUserId(request.getProductId(), request.getUserId());
        if(cart==null){
            cartRepository.save(mapTocart(request));
            return "Cart saved successful!";
        }else {
            cart.setQuantity(cart.getQuantity()+request.getQuantity());
            cartRepository.save(cart);
            return "Cart updated successful!";
        }

    }

    public List<CartResponse> getAllCartsByUserId(String id){
        List<Cart> cartList = cartRepository.findByUserId(id);
        return cartList.stream().map(this::mapTocartResponse).toList();
    }

    private CartResponse mapTocartResponse(Cart cart){
        return CartResponse.builder()
                .id(cart.getId())
                .quantity(cart.getQuantity())
                .productId(cart.getProductId())
                .userId(cart.getUserId())
                .build();
    }

    private Cart mapTocart(CartRequest cart){
        return Cart.builder()
                .quantity(cart.getQuantity())
                .productId(cart.getProductId())
                .userId(cart.getUserId())
                .build();
    }
}
