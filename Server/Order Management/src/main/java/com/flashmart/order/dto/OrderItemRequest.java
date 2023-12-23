package com.flashmart.order.dto;

import com.flashmart.order.model.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemRequest {

    private String orderId;
    private String productId;
    private double quantity;
    private double unitPrice;
    private double discount;
}
