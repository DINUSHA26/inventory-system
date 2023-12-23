import React, { useEffect, useState } from 'react'
import Food from "../../assets/images/catrgory/food.jpg"
import {BsCartCheck} from "react-icons/bs"
import {  getAllProducts } from '../../helper/inventory'
import Logo from "../../assets/icons/logo.png"
const Product = () => {

  const [activeCategory, setActiveCategory] = useState({
    categoryId:"", icon:"", name:"", productList:[]
  });
  const [categoryItems, setCategoryItems] = useState([])

  useEffect(()=>{
    const retrieveData = async ()=>{
      const data =await getAllProducts();
    setCategoryItems(data);
    setActiveCategory(data[0])
    };
    retrieveData();
  },[])
  
  return (
    <section className='product-container'>
      <div className="title">Categories</div>
      <div className="category-card-container">
       {categoryItems.map((item,index)=>(
        <div className={activeCategory.categoryId===item.categoryId?"active-category-card":"category-card"} key={index} onClick={()=>{setActiveCategory(item)}}>
        <img src={item.icon || Logo}/>
         <span>{item.name}</span>
        </div>
       ))}
      </div>

      <div className="title">Products</div>
      <div className="product-card-container">
        
         {activeCategory.productList.map((item,index)=>{
          if(item.category.categoryId === activeCategory.categoryId){
            return (
              <div className="product-card" key={index}>
              <img src={item.icon?item.icon:Logo}/>
              <div className="tag">
              <span>{item.name}</span>
              <div className="right-info">
              <span>Rs. {item.unitPrice}</span>
              <div className='add-to-cart'><span>Add to cart</span> <BsCartCheck/></div>
             
              </div>
               </div>
            </div>
            )
          }
        })} 
      </div>
    </section>
  )
}

export default Product
