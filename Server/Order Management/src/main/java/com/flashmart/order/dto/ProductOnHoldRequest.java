package com.flashmart.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductOnHoldRequest {

    private String productId;
    private double quantity;
    private Date holdTime;
}
