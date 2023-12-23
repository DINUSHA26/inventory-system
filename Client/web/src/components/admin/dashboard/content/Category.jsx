import React, { useEffect, useState } from "react";
import Logo from "../../../../assets/icons/logo.png"
import { useNavigate } from "react-router-dom";
import CategoryModal from "../../../modals/CategoryModal";
import { getAllProducts } from "../../../../helper/inventory";

const Category = () => {
  const navigate = useNavigate();
  const [modalOpen, setModalOpen] = useState(false);
  const openCategoryModal = ()=>setModalOpen(true);

  const closeCategoryModal = async ()=>{setModalOpen(false);
    const data =await getAllProducts();
    setCategoryItems(data);
  };
  const [category, setCategory] = useState(  {  categoryId:"", icon:"", name:""},
  )

  const [categoryItems, setCategoryItems] = useState([])

  useEffect(()=>{
    const retrieveData = async ()=>{
      const data =await getAllProducts();
    setCategoryItems(data);
    };
    retrieveData();
  },[])

  return (
    <section className="category-container">
      <div className="header">
        <div className="title">Product Categories</div>
        <div className="btn" onClick={openCategoryModal}>Add Category</div>
      </div>
      <div className="category-list">
        {categoryItems.map((item, index) => (
          <div className="category-item" key={index}>
            <div className="info">
            <img src={item.icon || Logo} />
            <div className="data">
              <div className="value category-type">{item.name}</div>
            </div>
            </div>

            <div className="data">
              <div className="name">No. of products: </div>
              <div className="value">{item.productList.length} </div>
            </div>

          
              <div className="options">
              <div className="btn option" onClick={()=>{
                setCategory(item);
                openCategoryModal();
              }}><span>Edit</span> </div>
              <div className="btn option" onClick={()=>navigate(`/admin/dashboard/category/${item.categoryId}`)}><span>View</span> </div>
              </div>
          </div>
        ))}
      </div>
      {modalOpen && <CategoryModal closeModal={closeCategoryModal} data={category}/>}
    </section>
  );
};

export default Category;
