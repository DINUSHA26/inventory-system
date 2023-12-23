import { userUrl } from "../Apis/baseUrl";

async function registerCustomer(data) {
  try {
    console.log(data);
   const res = userUrl.post("register/customer", data);
   return res;
  } catch (error) {
    console.error(error);
    alert(error);
  }
}

async function loginCustomer(data) {
  try {
    console.log(data);
   const res =await userUrl.post("login/customer", data);
   console.log(res.data)
   return res.data;
  } catch (error) {
    console.error(error);
    alert(error);
  }
}

async function generateOtp(id) {
  try {
   const res =await userUrl.get("otp/generate/"+id );
   console.log(res.data)
   return res.data;
  } catch (error) {
    console.error(error);
    alert(error);
  }
}

async function verifyOtp(id, otp) {
  try {
   const res =await userUrl.get(`otp/generate/${id}/${otp}` );
   console.log(res.data)
   return res.data;
  } catch (error) {
    console.error(error);
    alert(error);
  }
}



export {registerCustomer, loginCustomer, generateOtp, verifyOtp}