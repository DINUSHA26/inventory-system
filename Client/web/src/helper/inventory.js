import Swal from "sweetalert2";
import { inventoryUrl } from "../Apis/baseUrl";

async function getAllProducts() {
  try {
    const res = await inventoryUrl.get("category/productsByCategory");
    const data = res.data;
    console.log(data)
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function addNewCategory(data){
    try {
        const res = await inventoryUrl.post("category/newCategory", data);
        console.log(res);
        return Swal.fire({
            position: "top-end",
            icon: "success",
            title: "New category has been added succesfully",
            showConfirmButton: false,
            timer: 1500
          });;
    } catch (error) {
        console.log(error);

    }
}

async function getAllCategories(){
  try {
    const res = await inventoryUrl.get("category/");
    const data = res.data;
    console.log(data)
    return data;
  } catch (error) {
    console.log(error);
  }
}

async function addNewProduct(data){
  try {
      const res = await inventoryUrl.post("product/newProduct", data);
      console.log(res);
      return Swal.fire({
          position: "top-end",
          icon: "success",
          title: "New product has been added succesfully",
          showConfirmButton: false,
          timer: 1500
        });
  } catch (error) {
      console.log(error);

  }
}


export { getAllProducts, addNewCategory, getAllCategories, addNewProduct };
