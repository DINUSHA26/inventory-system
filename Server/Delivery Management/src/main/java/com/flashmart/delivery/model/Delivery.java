package com.flashmart.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value = "DeliveryUser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery {

    @Id
    private String id;
    private String orderId;
    private String userId;
    private Date pickedTime;
    private Date arrivedTime;
    private Date deliveredTime;
}
