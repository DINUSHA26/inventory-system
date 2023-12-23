package com.flashmart.user.service;

import com.flashmart.user.Consts.USER_TYPES;
import com.flashmart.user.config.WebClientConfig;
import com.flashmart.user.dto.HttpResponse;
import com.flashmart.user.dto.UserRequest;
import com.flashmart.user.dto.UserResponse;
import com.flashmart.user.model.User;
import com.flashmart.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final WebClientConfig webClient;
    private boolean verifyEmail(int type, String email){
        return userRepository.verifyEmail(type,email);
    }
    public HttpResponse newUser(UserRequest request){

        HttpResponse response = HttpResponse.builder().status(201).build();
        if(request!=null){
           if(!verifyEmail(request.getType(), request.getEmail())){
               User user = User.builder()
                       .email(request.getEmail())
                       .fname(request.getFname())
                       .lname(request.getLname())
                       .mobile(request.getMobile())
                       .password(encoder.encode(request.getPassword()))
                       .type(request.getType())
                       .build();

               User savedUser =  userRepository.save(user);
               response.setMessage(savedUser.getUserid());
               switch (request.getType()){
                   case USER_TYPES.DELIVERY_PERSON:

                       try {
                          String data =  webClient.webClientBuilder().build().post()
                                  .uri("http://delivery-service/api/delivery/user/{id}", savedUser.getUserid() )
                                  .retrieve()
                                  .bodyToMono(String.class)
                                  .block();
                          return response;


                       }catch (Exception e){
                           System.out.println(e.getMessage());
                       }
                       break;
                   case USER_TYPES.ADMIN:
                       return response;
                   case USER_TYPES.CUSTOMER:
                       return response;
                   default: break;

               }
           }else {
               response.setMessage(request.getEmail()+" is already registered");
               response.setStatus(500);
               return response;
           }
        }
        response.setMessage("Error occurred");
        response.setStatus(500);
        return response;
}

public HttpResponse loginUser(UserRequest request){

        User validUser = userRepository.findByTypeAndEmail(request.getType(), request.getEmail());
    System.out.println(validUser);
        HttpResponse response = HttpResponse.builder()
                .status(500)
                .build();
        if (validUser!=null){
            String hashedPassword = validUser.getPassword();
            String password = request.getPassword();
            if(encoder.matches(password, hashedPassword)){
                response.setStatus(200);
                response.setMessage(validUser.getUserid());
                return response;

        }else {
                response.setStatus(500);
                response.setMessage("Given username and password did not matched");
            }
}
    response.setStatus(404);
    response.setMessage("User Not found");
    return response;

}
}
