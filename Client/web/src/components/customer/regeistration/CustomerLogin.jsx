import React,{useState} from 'react'
import DeliveryPerson from "../../../assets/images/deliveryPerson1.jpg"
import LOGO from "../../../assets/icons/logo.png"
import "../../../styles/customer/login.css"
import { useNavigate } from 'react-router-dom'
import baseUrl from '../../../Apis/baseUrl'
import { loginCustomer } from '../../../helper/registration'
import Swal from 'sweetalert2'
const CustomerLogin = () => {
    const navigate = useNavigate();
   const [loginForm, setLoginForm] = useState({email:"", password:""});

    const handleChange = (event) => {
      const { name, value } = event.target;
      setLoginForm((prevValues) => ({
        ...prevValues,
        [name]: value,
      }));
    }

    const handleSubmit = async (e) => {
      e.preventDefault();
      
      
        try {
          const data = await loginCustomer(loginForm);
          if(data.status === 200){
            localStorage.setItem("token", data.message)
            return Swal.fire({
              icon: "success",
              title: "Login succesfull",
              text: `Welcome back ${loginForm.email}`,
            }).then(async (res)=>{
              
              navigate(`/`)
            });
          }else{
            return Swal.fire({
              icon: "error",
              title: "Login failed",
              text: data.message,
            })
          }
        } catch (error) {
          console.error(error);
        }
      
    };

  return (
    <div className='customer-login-container'>
      <div className="left">
        <span className='left-title'>Welcome Back!</span>
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

      <form action="" className="login-form" onSubmit={handleSubmit}>
        <input type="email" name="email" className="field" required placeholder='Enter your email'onChange={handleChange}/>
        <input type="password" name="password" className="field" required placeholder='Enter your password'onChange={handleChange}/>
        <button type="submit" className="btn input-btn" >Login</button>
        <div className="login-options">Don't have an FLashMart account? <span onClick={()=>navigate("/customer/register")}>Register</span></div>
      </form>
      </div>
    </div>
  )
}

export default CustomerLogin
