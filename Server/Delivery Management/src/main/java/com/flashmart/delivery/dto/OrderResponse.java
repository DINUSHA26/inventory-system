package com.flashmart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private String orderId;
    private String customerId;
    private Date orderPlacedDate;
    private int status;
    private double totalAmount;
    private String deliveryId;
    private double discount;
    private double latitude;
    private double longitude;
    private String paymentId;

}
