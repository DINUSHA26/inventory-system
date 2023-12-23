package com.flashmart.order.dto;

import com.flashmart.order.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequest {
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
