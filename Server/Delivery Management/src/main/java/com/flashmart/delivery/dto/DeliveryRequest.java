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
public class DeliveryRequest {
    private String orderId;
    private String userId;
    private Date pickedTime;
    private Date arrivedTime;
    private Date deliveredTime;
}
