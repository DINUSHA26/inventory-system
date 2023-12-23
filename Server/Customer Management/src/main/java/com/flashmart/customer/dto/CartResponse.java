package com.flashmart.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponse {
    private String id;
    private String userId;
    private String productId;
    private double quantity;
}
