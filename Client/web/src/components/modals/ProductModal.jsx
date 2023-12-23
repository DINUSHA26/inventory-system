import React, { useEffect, useRef, useState } from "react";
import "../../styles/modal.css";
import ImagePlaceHolder from "../shared/ImagePlaceHolder";
import ImageUpload from "../shared/ImageUpload";
import onUpload from "../../helper/upload";
import { addNewProduct, getAllCategories } from "../../helper/inventory";

const ProductModal = ({ closeModal, data }) => {
  const [progress, setProgress] = useState(0);
  const fileInputRef = useRef(null);
  const [file, setFile] = useState();
  const [downloadLink, setDownloadLink] = useState(data.icon);
  const [categoryList, setCategoryList] = useState([]);
  console.log(data);
  const [product, setProduct] = useState(data?
    {
      categoryId:data.category.categoryId,
      name:data.name,
      icon:data.icon,
      unitPrice:data.unitPrice,
      stockQuantity:data.stockQuantity,
      bufferQuantity:data.bufferQuantity,
      measureType: 1001,
  
    }:
    {
      categoryId:"",
      name:"",
      icon:"",
      unitPrice:0,
      stockQuantity:0,
      bufferQuantity:0,
      measureType: 1001,
  
    });
  console.log(data)
  const handleFileChange = (event) => {
    const newFile = event.target.files[0];

    if (newFile) {
      // Handle the selected file here
      setFile(newFile);
      setProgress(0);
      onUpload(newFile, "/product", setProgress, setDownloadLink);
    }
  };

  const handleImageSelect = () => {
    fileInputRef.current.click();
  };

 

  const handleChange = (event) => {
    const { name, value } = event.target;
    setProduct((prevValues) => ({
      ...prevValues,
      [name]: value,
    }));
  };

  async function handleSubmit(e) {
    e.preventDefault();
    const data = product;
    data.icon = downloadLink;
    console.log(product);
    try {
      await addNewProduct(product);
      closeModal();
    } catch (error) {
      
    }


  }

  useEffect(()=>{
    const retrieveData = async ()=>{
      const data =await getAllCategories();
    setCategoryList(data);
    };
    retrieveData();
  },[])

  return (
    <div className="modal-container">
      <div className="modal-header">
        <div className="modal-title">Category</div>
        <div className="btn-close" onClick={closeModal}>
          X
        </div>
      </div>

      <div className="modal-body">
        <div className="left">
          <div className="figure" onClick={handleImageSelect}>
            {data.image || downloadLink ? (
              // <div><img ref={fileInputRef}  src={URL.createObjectURL(file) || data.image} /></div>
              <div>
                <img ref={fileInputRef} src={downloadLink || data.image} />
              </div>
            ) : (
              <div ref={fileInputRef}>
                <ImagePlaceHolder />
              </div>
            )}
          </div>

          <input
            type="file"
            ref={fileInputRef}
            accept="image/*"
            style={{ display: "none" }}
            onChange={handleFileChange}
          />

          {progress > 0 && progress < 100 && (
            <ImageUpload
              filename={file.name}
              size={file.size}
              precentage={progress}
            />
          )}
        </div>
        <form className="right-form" onSubmit={handleSubmit}>
          <div className="input-item">
            <div className="label">Product Category</div>
            <select className="input-select" name="categoryId" value={product.categoryId} onChange={handleChange}>
              <option className="input-select-option" value="">All Categories</option>
              {categoryList.map((category, index) => (
                <option key={index} value={category.categoryId}>
                  Category {category.name}
                </option>
              ))}
            </select>
          </div>

          <div className="input-item">
            <div className="label">Product Name</div>
            <input type="text" placeholder="Product Name" value={product.name} name="name" className="field" onChange={handleChange}/>
          </div>

          <div className="input-item">
            <div className="label">Unit Price</div>
            <input  type="number" placeholder="Product price"value={product.unitPrice}  name="unitPrice" className="field" onChange={handleChange}/>
          </div>

          <div className="input-item">
            <div className="label">Stock Quantity</div>
            <input  type="number" placeholder="Product price"value={product.stockQuantity}  name="stockQuantity" className="field" onChange={handleChange}/>
          </div>

          <div className="input-item">
            <div className="label">Buffer Quantity</div>
            <input  type="number" placeholder="Product price"value={product.bufferQuantity}  name="bufferQuantity" className="field" onChange={handleChange}/>
          </div>

          <div className="input-item">
            <button className="btn" type="submit">Submit</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ProductModal;
