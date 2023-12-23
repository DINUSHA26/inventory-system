package com.flashmart.delivery.service;

import com.flashmart.delivery.config.WebClientConfig;
import com.flashmart.delivery.consts.ORDER_STATUS;
import com.flashmart.delivery.dto.OrderResponse;
import com.flashmart.delivery.model.Delivery;
import com.flashmart.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final WebClientConfig webClient;
    private final DeliveryRepository deliveryRepository;
    public List<OrderResponse> getAllAvailableOrders(){
        ResponseEntity<List<OrderResponse>> response =    webClient.webClientBuilder().build().get()
                .uri("http://order-service/api/order/{status}", ORDER_STATUS.CONFIGURING)
                .retrieve()
              .toEntityList(OrderResponse.class)
                .block();

        assert response != null;
        if (response.getStatusCode().is2xxSuccessful()) {
            // Return the list of orders
            return response.getBody();
        } else {
            // Handle the case where the external service returns an error
            // You may throw an exception or return an empty list depending on your use case
            return Collections.emptyList();
        }
    }

    public String acceptOrder(String orderId, String userId){


        Delivery delivery = deliveryRepository.save(
                Delivery.builder()
                 .orderId(orderId)
                 .userId(userId)
                 .build());

        String res = webClient.webClientBuilder().build().get()
                .uri("http://order-service/api/order/accept/{orderId}/{userId}", orderId, userId)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if(res==null){
            throw new RuntimeException("Error occurred");
        }
        return res;
    }

    public String updateOrderStatus(String userId, String orderId, int status){
        Delivery delivery = deliveryRepository.findByOrderId(orderId);
        if(delivery!=null && Objects.equals(delivery.getOrderId(), orderId) && Objects.equals(delivery.getUserId(), userId)){


            if (status==ORDER_STATUS.PICKED)
            delivery.setPickedTime(new Date());
            if (status==ORDER_STATUS.ARRIVED)
                delivery.setArrivedTime(new Date());
            if (status==ORDER_STATUS.DELIVERED)
                delivery.setDeliveredTime(new Date());

            deliveryRepository.save(delivery);

            return  webClient.webClientBuilder().build().put()
                    .uri("http://order-service/api/order/status/{orderId}/{status}", orderId, status)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }else {
            return "Order for "+orderId + "couldn't find. " + "Order status update to "+ORDER_STATUS.toMessage(status)+" failed";
        }

    }

}
