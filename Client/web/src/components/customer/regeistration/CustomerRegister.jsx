import React,{ useState } from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson2.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import "../../../styles/error.css"
import { useNavigate } from 'react-router-dom'
import USER_TYPES from '../../../const/USER_TYPES'
import baseUrl from '../../../Apis/baseUrl'
import { generateOtp, registerCustomer } from '../../../helper/registration'
import Swal from 'sweetalert2'



const CustomerRegister = () => {
    const navigate = useNavigate();
    const [form, setForm] = useState({
      fname:"",
      lname:"",
      mobile:"",
      email:"",
      password:""
    })
    const [cpsw, setCpsw] = useState("");


    const handleChange = (event) => {
      const { name, value } = event.target;
      setForm((prevValues) => ({
        ...prevValues,
        [name]: value,
      }));
    }
    

    const handleSubmit = async (e) => {
      e.preventDefault();
      
      if(form.password!= cpsw){
        alert("Your confirm password did not matched!")
      }else{
        try {
          const res = await registerCustomer(form);
          const data = res.data;
          if(data.status === 201){
            return Swal.fire({
              icon: "success",
              title: "Registration succesfull",
              text: "Welcome "+ form.fname + " "+ form.lname+" to the flashmart.",
            }).then(async (res)=>{
              await generateOtp(data.message);
              navigate(`/customer/otp/${data.message}`)
            });
          }
        } catch (error) {
          console.error(error);
        }
      }
    };

  return (
    <div>
       <div className='customer-login-container'>
      <div className="left">
      <span className='left-title'>Register Now!</span>
        <img src={DeliveryPerson}/>

      </div>
      <div className="right">
      <div className="header">
        <img src={LOGO}/>
        <span>FlashMart</span>
      </div>
      <div className="sub-title">
        <div><span>S</span>elect</div> <div><span>B</span>uy</div> <div><span>D</span>eliver</div>
      </div>

      <form action="post" className="login-form" onSubmit={handleSubmit}>
        <div className="input-names">
        <input type="text" name='fname'  className="field" required placeholder='First Name' onChange={handleChange}/>
        <input type="text" name='lname' className="field" required placeholder='Last Name' onChange={handleChange}/>
        </div>
        <input type="email" className="field" name='email' required placeholder='Enter your email'onChange={handleChange}/>
        <input type="tel" className="field" name='mobile' required placeholder='Contact number'onChange={handleChange}/>

        <input type="password" className="field" name='password' required placeholder='Enter your password' onChange={handleChange}/>
        <input type="password" className="field" required placeholder='Re-enter your password' onChange={(e)=>setCpsw(e.target.value)}/>


        <button type="submit" className="btn input-btn" >Register</button>
        <div className="login-options">Already have an FLashMart account? <span onClick={()=>navigate("/customer/login",{state:{email:"testmail@gmail.com"}})}>Login</span></div>
      </form>
      </div>
    </div>
    </div>
  )
}

export default CustomerRegister
