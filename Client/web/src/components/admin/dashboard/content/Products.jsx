import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ProductModal from "../../../modals/ProductModal";
import { getAllProducts } from "../../../../helper/inventory";

const Products = () => {
  const { id } = useParams();
  console.log(id);
  const [containerStyle, setContainerStype] = useState({
    background: `black`,
  });

  const [modalOpen, setModalOpen] = useState(false);
  const [activeCategory, setActiveCategory] = useState({
    categoryId: "",
    icon: "",
    name: "",
    productList: [],
  });
  const [product, setProduct] = useState({
    productId: "",
    name: "",
    icon: "",
    category: null,
    measureType: "",
    unitPrice: 0,
    stockQuantity: 0,
    bufferQuantity: 0,
  });

  const openProductModal = () => setModalOpen(true);

  const closeProductModal = async () => {
    setProduct({ productId: "",
    name: "",
    icon: "",
    category: activeCategory.categoryId,
    measureType: "",
    unitPrice: 0,
    stockQuantity: 0,
    bufferQuantity: 0,});
    setModalOpen(false);
    const data = await getAllProducts();
      const category = data.find((category) => category.categoryId === id);
      setActiveCategory(category);
      setContainerStype({
        background: `url(${category.icon}) no-repeat center center fixed`,
        backgroundSize: "cover",
      });

      setProduct((prevValues) => ({
        ...prevValues,
        category: category,
      }));
    
  }

  useEffect(() => {
    const retrieveData = async () => {
      const data = await getAllProducts();
      const category = data.find((category) => category.categoryId === id);
      setActiveCategory(category);
      setContainerStype({
        background: `url(${category.icon}) no-repeat center center fixed`,
        backgroundSize: "cover",
      });

      setProduct((prevValues) => ({
        ...prevValues,
        category: category,
      }));
    };
    retrieveData();
  }, []);

  return (
    <section className="product-container">
      <div className="header" style={containerStyle}>
        <div className="bg-filter">
          <div className="title"> {activeCategory.name}</div>
          <div className="product-info-header">
            <div className="no-of-products">
              <div className="name">No. of Products:</div>{" "}
              <div className="value">{activeCategory.productList.length}</div>
            </div>
            <div className="no-of-products">
              <div className="name">No. of Products in Stock: </div>
              <div className="value">
                {
                  activeCategory.productList.filter(
                    (pitem) => pitem.stockQuantity >= pitem.bufferQuantity
                  ).length
                }
              </div>
            </div>
            <div className="no-of-products">
              <div className="name"> </div>
              <div className="btn value" onClick={openProductModal}>
                New Product
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="product-list">
        {activeCategory.productList.map((item, index) => {
          return (
            <div className="product-item" key={index}>
              <div className="product-header">
                <img src={item.icon} />
                <div className="product-info">
                  <div className="data">
                    <div className="name">Name: </div>
                    <div className="value">{item.name}</div>
                  </div>
                  <div className="data">
                    <div className="name">Unit Price: </div>
                    <div className="value">Rs. {item.unitPrice}</div>
                  </div>
                  <div className="data">
                    <div className="name">Available Quantity: </div>
                    <div className="value"> {item.stockQuantity} </div>
                  </div>
                </div>
              </div>
              <div className="status">
                <div className="name">Status: </div>
                <div className="value">
                  {item.stockQuantity >= item.bufferQuantity
                    ? "In Stock"
                    : "Out of Stock"}
                </div>
              </div>

              <div className="options">
                <div
                  className="btn option"
                  onClick={() => {
                    setProduct(item);
                    openProductModal();
                  }}
                >
                  Update Details
                </div>
                <div className="btn-warning option">Remove</div>
              </div>
            </div>
          );
        })}
      </div>
      {modalOpen && (
        <ProductModal closeModal={closeProductModal} data={product} />
      )}
    </section>
  );
};

export default Products;
