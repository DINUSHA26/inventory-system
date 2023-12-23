package com.flashmart.order.service;

import com.flashmart.order.config.WebClientConfig;
import com.flashmart.order.consts.ORDER_STATUS;
import com.flashmart.order.dto.*;
import com.flashmart.order.model.OrderEntity;
import com.flashmart.order.model.OrderItem;
import com.flashmart.order.repository.OrderItemRepository;
import com.flashmart.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final WebClientConfig webClient;

    @Transactional
    public String createNewOrder(PlaceOrderRequest request){

        OrderEntity order = mapToOrder(request);

        List<OrderItem> itemList = request.getOrderItems();
        order.setStatus(ORDER_STATUS.CONFIGURING);
        order.setOrderPlacedDate(new Date());
        System.out.println(order);
        OrderEntity placedOrder = orderRepository.save(order);

        List<ProductOnHoldRequest> requestList = new ArrayList<>();
        for(OrderItem item: itemList){
            //Check the stock in inventory
            //item.setOrder(placedOrder);
            requestList.add(ProductOnHoldRequest.builder()
                            .productId(item.getProductId())
                            .quantity(item.getQuantity())
                    .build());

               String data = webClient.webClientBuilder().build().put()
                        .uri("http://inventory-service/api/inventory/products/onHold" )
                        .body(BodyInserters.fromValue(requestList))
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

            if(data==null) throw new RuntimeException("Couldn't move the inventory to the onHold");

        }

        return placedOrder.getOrderId()+" Order placed successfully! and "+ request.getOrderItems().size()+" products has been added to the order.";

    }

    public OrderResponse findOrderById(String id){
        return mapToOrder(orderRepository.findById(id).orElseThrow(()->new RuntimeException("404 error, Order did not found")));
    }

    public String assignToDeliver(String orderId, String userId){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("404 error, Order did not found"));
        order.setDeliveryId(userId);
        order.setStatus(ORDER_STATUS.PREPARING);
        orderRepository.save(order);

        return "Order "+orderId+" has been assign to user "+userId;

    }

    public String updateOrderStatus(String orderId, int status){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("404 error, Order did not found"));
        int currentStatus = order.getStatus();

        if(status<=currentStatus) throw new RuntimeException("Given status must me a next status. Cannot be go back to the previous status.");
        order.setStatus(status);
        orderRepository.save(order);

        return "Order "+orderId+" has been changed it's status from "+ORDER_STATUS.toMessage(currentStatus)+" to "+ORDER_STATUS.toMessage(order.getStatus());

    }
    public List<OrderResponse> getOrdersByStatus(int status){
        System.out.println(status);
        List<OrderEntity> orderList = orderRepository.findByStatus(status);
        System.out.println(orderList);
        return orderList.stream().map(this::mapToOrder).toList();
    }

    private OrderEntity mapToOrder(PlaceOrderRequest request){

        return OrderEntity.builder()
                .customerId(request.getCustomerId())
                .orderPlacedDate(request.getOrderPlacedDate())
                .status(request.getStatus())
                .totalAmount(request.getTotalAmount())
                .deliveryId(request.getDeliveryId())
                .discount(request.getDiscount())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .paymentId(request.getPaymentId())
                .build();
    }

    private OrderResponse mapToOrder(OrderEntity request){

        return OrderResponse.builder()
                .orderId(request.getOrderId())
                .customerId(request.getCustomerId())
                .orderPlacedDate(request.getOrderPlacedDate())
                .status(request.getStatus())
                .totalAmount(request.getTotalAmount())
                .deliveryId(request.getDeliveryId())
                .discount(request.getDiscount())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .paymentId(request.getPaymentId())
                .build();
    }

    private OrderItem mapToOrderItem(OrderItemRequest item){
        return OrderItem.builder()
                //.order(item.getOrder())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .discount(item.getDiscount())
                .build();
    }
}
