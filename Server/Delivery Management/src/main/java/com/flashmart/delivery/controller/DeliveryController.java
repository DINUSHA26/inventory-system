package com.flashmart.delivery.controller;

import com.flashmart.delivery.consts.ORDER_STATUS;
import com.flashmart.delivery.dto.OrderResponse;
import com.flashmart.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/orders")
    public List<OrderResponse> getAllAcceptableOrders(){
        return deliveryService.getAllAvailableOrders();
    }

    @GetMapping("/accept/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String assignToDelivery(@PathVariable String orderId, @PathVariable String userId){
        return deliveryService.acceptOrder(orderId,userId);
    }

    @PutMapping("/pickedUp/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String pickUpTheOrder(@PathVariable String orderId, @PathVariable String userId){
        return deliveryService.updateOrderStatus(userId,orderId, ORDER_STATUS.PICKED);
    }

    @PutMapping("/arrived/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String arrivedTheOrder(@PathVariable String orderId, @PathVariable String userId){
        return deliveryService.updateOrderStatus(userId,orderId, ORDER_STATUS.ARRIVED);
    }

    @PutMapping("/deliver/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deliveredTheOrder(@PathVariable String orderId, @PathVariable String userId){
        return deliveryService.updateOrderStatus(userId,orderId, ORDER_STATUS.DELIVERED);
    }
}
