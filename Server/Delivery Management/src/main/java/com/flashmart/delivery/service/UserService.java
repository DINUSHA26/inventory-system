package com.flashmart.delivery.service;

import com.flashmart.delivery.consts.DELIVERY_STATUS;
import com.flashmart.delivery.dto.UserRequest;
import com.flashmart.delivery.dto.UserResponse;
import com.flashmart.delivery.model.User;
import com.flashmart.delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DistanceService distanceService = new DistanceService();

    public String createDeliveryUser(String id){

        System.out.println(id+" from del");
        if(id!=null){
            User user = User.builder()
                    .userId(id)
                    .ratedUsers(0)
                    .latitude(0)
                    .rating(0)
                    .longitude(0)
                    .vehicleId(null)
                    .status(DELIVERY_STATUS.UNAVAILABLE)
                    .build();

            userRepository.save(user);
            return "Delivery user saved successfully";
        }
        return "";
    }

    public UserResponse getAvailableUser(){
        List<User> userList = userRepository.findByStatus(DELIVERY_STATUS.AVAILABLE);

        Collections.sort(userList, (u1,u2)->Double.compare(
               distanceService.calculateDistance(u1.getLatitude(), u1.getLongitude()),
                distanceService.calculateDistance(u2.getLatitude(), u2.getLongitude())
        ));
        if(!userList.isEmpty()){
            return mapToResponse(userList.get(0));
        }
        return null;
    }



    private UserResponse mapToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .ratedUsers(user.getRatedUsers())
                .rating(user.getRating())
                .vehicleId(user.getVehicleId())
                .status(user.getStatus())
                .longitude(user.getLongitude())
                .latitude(user.getLatitude())
                .build();
    }
}
