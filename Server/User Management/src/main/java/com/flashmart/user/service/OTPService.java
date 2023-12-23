package com.flashmart.user.service;

import com.flashmart.user.Consts.OTP;
import com.flashmart.user.dto.HttpResponse;
import com.flashmart.user.otp.OtpModel;
import org.springframework.stereotype.Service;

@Service
public class OTPService {

    private static final OtpModel otp = OtpModel.getInstance();


    public String generateOtp (String id){
       String otpCode = otp.generateOtp(id);
       if(otpCode!=null){
           return "OTP Generated: "+otpCode;
       }else {
           return "OTP Generated failed. Error occurred";
       }
    }

    public HttpResponse verifyOtp(String id, String otpCode){
        int otpStatus = otp.verifyOtp(id,otpCode);
        HttpResponse response = HttpResponse.builder()
                .status(500)
                .build();
        if(otpStatus == OTP.VERIFIED){
            response.setStatus(200);
            response.setMessage("OTP Verified!");
            return response;
        }else if(otpStatus == OTP.UNVERIFIED){
            response.setMessage("OTP Verified failed.");
            return response;
        }else if(otpStatus == OTP.EXPIRED){
            response.setMessage("OTP Verified failed. OTP expired");
            return response;
        }
        else {
            response.setMessage("OTP Verification failed. Error occurred");
            return response;
        }
    }
}
