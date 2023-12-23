import axios from "axios";

const userUrl = axios.create({
  baseURL: "http://localhost:8080/api/user/",
  
});

const customerUrl = axios.create({
  baseURL: "http://localhost:8080/api/customer/",
  
});

const inventoryUrl = axios.create({
  baseURL: "http://localhost:8080/api/inventory/",
  
});

const deliveryUrl = axios.create({
  baseURL: "http://localhost:8080/api/delivery/",
  
});

const orderUrl = axios.create({
  baseURL: "http://localhost:8080/api/order/",
  
});

export  {userUrl, customerUrl, inventoryUrl, deliveryUrl, orderUrl}
