import React, { useState } from "react";
import OTP from "../../../assets/images/otp.jpg";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import "../../../styles/customer/login.css";
import { verifyOtp } from "../../../helper/registration";
import Swal from "sweetalert2";

const OTPVerification = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [otp, setOtp] = useState(0);

  const handleChange=(e)=>{
    const otp = e.target.value;
    if(otp.length<6){
      setOtp(otp);
    }
  }
  const handleSubmit =async ()=>{
    const res = await verifyOtp(id, otp);
    if(res.status===200){
      return Swal.fire({
        icon: "success",
        title: "Registration succesfull",
        text: res.message,
      }).then(async (res)=>{
        localStorage.setItem("token", id)
        navigate("/");      });
    }else{
      return Swal.fire({
        icon: "error",
        title: "Verification failed!",
        text: res.message,
      })
    }
  }
  return (
    <div className="otp-container">
      <div className="header">
        <img src={OTP} />
        <span>OTP Verification</span>
        <div className="sub-title">
          One time Password has been sent to your registered email address.
        </div>
        <input className="otp-field" value={otp} placeholder="Enter OTP" onChange={handleChange}/>
      </div>

      <div className="bottom">
        <div className="no-otp">
          Don't you recieved the OTP? <span>Resend OTP</span>
        </div>
        <div
          className="btn verify-otp"
          onClick={() => {
            handleSubmit();
          }}
        >
          Verify
        </div>
      </div>
    </div>
  );
};

export default OTPVerification;
