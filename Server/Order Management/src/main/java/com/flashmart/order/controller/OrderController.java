package com.flashmart.order.controller;

import com.flashmart.order.dto.OrderItemRequest;
import com.flashmart.order.dto.OrderRequest;
import com.flashmart.order.dto.OrderResponse;
import com.flashmart.order.dto.PlaceOrderRequest;
import com.flashmart.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody PlaceOrderRequest request){
        return  orderService.createNewOrder(request);
    }

    @GetMapping("/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderesByStatus(@PathVariable int type){
        return orderService.getOrdersByStatus(type);
    }

    @GetMapping("/accept/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String assignToDeliver(@PathVariable String orderId, @PathVariable String userId){
        return orderService.assignToDeliver(orderId,userId);
    }

    @PutMapping("/status/{orderId}/{status}")
    @ResponseStatus(HttpStatus.OK)
    public String updateStatus(@PathVariable String orderId, @PathVariable int status){
        return orderService.updateOrderStatus(orderId, status);
    }
}
