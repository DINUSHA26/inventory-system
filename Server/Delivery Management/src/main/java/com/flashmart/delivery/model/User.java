package com.flashmart.delivery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "DeliveryUser")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    private String id;
    private String userId;
    private int status;
    private String vehicleId;
    private int rating;
    private int ratedUsers;
    private double latitude;
    private double longitude;

}
