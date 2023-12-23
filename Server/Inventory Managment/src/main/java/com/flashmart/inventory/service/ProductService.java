package com.flashmart.inventory.service;

import com.flashmart.inventory.consts.INVENTORY_TRANSACTION;
import com.flashmart.inventory.consts.MEASURE_TYPE;
import com.flashmart.inventory.dto.CategoryResponse;
import com.flashmart.inventory.dto.ProductRequest;
import com.flashmart.inventory.dto.ProductResponse;
import com.flashmart.inventory.model.Category;
import com.flashmart.inventory.model.InventoryMovement;
import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.repository.CategoryRepository;
import com.flashmart.inventory.repository.InventoryMovementRepository;
import com.flashmart.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final InventoryMovementRepository inventoryMovementRepository;
    private final CategoryRepository categoryRepository;

    public String addNewProduct(ProductRequest request){

        InventoryMovement record = InventoryMovement.builder()
                .product(mapToProduct(request))
                .quantity(request.getStockQuantity())
                .type(INVENTORY_TRANSACTION.BUY_ON_CASH)
                .movementDate(new Date())
                .build();

        inventoryMovementRepository.save(record);
        return "Product "+request.getName()+" has been added succesfully!.";

    }

    public List<ProductResponse> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProductById(String id){
        return mapToProductResponse(productRepository.findById(id).orElseThrow());
    }

    public Product getProductforHold(String id){
        return (productRepository.findById(id).orElseThrow());
    }

    public List<ProductResponse> getProductsByCategory(String id){
        Category category = categoryRepository.findById(id).orElseThrow();
        List<Product> productList = productRepository.findByCategory(category);
        return productList.stream().map(this::mapToProductResponse).toList();
    }
    public String updateStockQuantityOfProduct(String id, double quantity, int type){
        Product product = productRepository.findById(id).orElseThrow();
        if(product.getStockQuantity() + quantity >= product.getBufferQuantity()){
            product.setStockQuantity(quantity+product.getStockQuantity());

            InventoryMovement record = InventoryMovement.builder()
                    .product(product)
                    .quantity(Math.abs(quantity))
                    .type(type)
                    .movementDate(new Date())
                    .build();

            inventoryMovementRepository.save(record);
            return  Math.abs(quantity)+" has been  "+INVENTORY_TRANSACTION.ToMessage(type)+", "+product.getName();
        }else {
            return  " stock must me at at least in buffer stock";
        }
    }
    private Product mapToProduct(ProductRequest request){
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        return Product.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .category(category)
                .measureType(request.getMeasureType())
                .bufferQuantity(request.getBufferQuantity())
                .unitPrice(request.getUnitPrice())
                .stockQuantity(request.getStockQuantity())
                .build();
    }

    private ProductResponse mapToProductResponse(Product request){
        return ProductResponse.builder()
                .productId(request.getProductId())
                .name(request.getName())
                .icon(request.getIcon())
                .category(request.getCategory())
                .measureType(MEASURE_TYPE.ToMessage(request.getMeasureType()))
                .bufferQuantity(request.getBufferQuantity())
                .unitPrice(request.getUnitPrice())
                .stockQuantity(request.getStockQuantity())
                .build();
    }



}
