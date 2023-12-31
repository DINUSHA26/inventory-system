import React, { useEffect, useRef, useState } from "react";
import "../../styles/modal.css";
import ImagePlaceHolder from "../shared/ImagePlaceHolder";
import ImageUpload from "../shared/ImageUpload";
import onUpload from "../../helper/upload";
import { addNewCategory } from "../../helper/inventory";

const CategoryModal = ({ closeModal, data }) => {
  const [progress, setProgress] = useState(0);
  const fileInputRef = useRef(null);
  const [file, setFile] = useState(null);
  const [downloadLink, setDownloadLink] = useState(null);
 // const { name, image, noOfProducts } = data;
  const [name, setName] = useState("");

  const handleFileChange = (event) => {
    const newFile = event.target.files[0];

    if (newFile) {
      // Handle the selected file here
      setFile(newFile);
      setProgress(0);
      onUpload(newFile, "/category", setProgress, setDownloadLink);
    }
  };

  const handleImageSelect = () => {
    fileInputRef.current.click();
  };

  const handleSubmit=async (e)=>{
    e.preventDefault();
    const data = {icon:downloadLink, name};
    console.log(data);
    try {
      await addNewCategory(data);
      closeModal();
    } catch (error) {
      
    }
  }

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
        <form onSubmit={handleSubmit} className="right-form">
          <div className="input-item">
            <div className="label">Category Name</div>
            <input type="text" value={name} onChange={(e)=>{setName(e.target.value)}} placeholder="Category Name" className="field" />
          </div>

          <div className="input-item">
            <button className="btn" type="submit">Submit</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CategoryModal;
