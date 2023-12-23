package com.flashmart.user.controller;

import com.flashmart.user.Consts.USER_TYPES;
import com.flashmart.user.dto.HttpResponse;
import com.flashmart.user.dto.UserRequest;
import com.flashmart.user.service.OTPService;
import com.flashmart.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OTPService otpService;

    @PostMapping("/register/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponse newCustomer(@RequestBody UserRequest request){
        request.setType(USER_TYPES.CUSTOMER);
        return userService.newUser(request);
    }

    @PostMapping("/register/delivery")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponse newDelivery(@RequestBody UserRequest request){
        request.setType(USER_TYPES.DELIVERY_PERSON);
        return userService.newUser(request);
    }

    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponse newAdmin(@RequestBody UserRequest request){
        request.setType(USER_TYPES.ADMIN);
        return userService.newUser(request);
    }

    @PostMapping("/login/customer")
    public HttpResponse loginCustomer(@RequestBody UserRequest request){
        request.setType(USER_TYPES.CUSTOMER);
        System.out.println(request);
        return userService.loginUser(request);
    }

    @PostMapping("/login/delivery")
    public HttpResponse loginDelivery(@RequestBody UserRequest request){
        request.setType(USER_TYPES.DELIVERY_PERSON);
        return userService.loginUser(request);
    }

    @PostMapping("/login/admin")
    public HttpResponse loginAdmin(@RequestBody UserRequest request){
        request.setType(USER_TYPES.ADMIN);
        return userService.loginUser(request);
    }

    @GetMapping("/otp/generate/{id}")
    public String generateOtp(@PathVariable String id){

        return otpService.generateOtp(id);
    }

    @GetMapping("/otp/generate/{id}/{otp}")
    public HttpResponse verifyOtp(@PathVariable String id, @PathVariable String otp){
        return otpService.verifyOtp(id, otp);
    }
}
