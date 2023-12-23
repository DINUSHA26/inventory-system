package com.flashmart.order.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
