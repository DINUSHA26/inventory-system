package com.flashmart.user.otp;

import com.flashmart.user.Consts.OTP;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.Objects;

public class OtpModel {

    private String otp;
    private String userId;
    private Date otpGeneratedTime;
    private int  otpStatus;

    private static OtpModel instance = null;

private OtpModel(){

}

    public static OtpModel getInstance(){
        if (instance==null){
            instance = new OtpModel();
        }
        return instance;
    }

    public String generateOtp(String userId){
    if(this.otp!=null) {
        long gap = (new Date().getTime() - instance.otpGeneratedTime.getTime()) / (60 * 1000);

        System.out.println(gap);
        if (gap < 2) {
            instance.otpStatus = OTP.EXIST;
        } else {
            this.otp = RandomStringUtils.randomNumeric(5);
            this.userId = userId;
            this.otpGeneratedTime = new Date();
            this.otpStatus = OTP.GENERATED;

            return this.otp;
        }
    }else {
        this.otp = RandomStringUtils.randomNumeric(5);
        this.userId = userId;
        this.otpGeneratedTime = new Date();
        this.otpStatus = OTP.GENERATED;
        return  this.otp;
    }

        return null;


    }

    public int verifyOtp(String userId, String otp){
        long gap = (new Date().getTime() - this.otpGeneratedTime.getTime())/(60*1000);
        if(gap>5){
            this.otpStatus = OTP.EXPIRED;
        }else {
            if (Objects.equals(this.otp, otp) && Objects.equals(this.userId, userId)){
                this.otpStatus = OTP.VERIFIED;
            }else {
                this.otpStatus = OTP.UNVERIFIED;
            }
        }
        return this.otpStatus;
    }
}
