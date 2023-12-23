package com.flashmart.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse {

    private String id;
    private String userId;
    private int status;
    private String vehicleId;
    private int rating;
    private int ratedUsers;
    private double latitude;
    private double longitude;
}
