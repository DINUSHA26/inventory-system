package com.flashmart.inventory.controller;

import com.flashmart.inventory.consts.INVENTORY_TRANSACTION;
import com.flashmart.inventory.dto.ProductRequest;
import com.flashmart.inventory.dto.ProductResponse;
import com.flashmart.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/newProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public String createNewProduct(@RequestBody  ProductRequest request){
        return productService.addNewProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return  productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getAllProductById(@PathVariable String id){
        return  productService.getProductById(id);
    }

    @PutMapping("/sellOnCash/{id}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public String sellOnCash(@PathVariable String id, @PathVariable double quantity){
        return productService.updateStockQuantityOfProduct(id, (0- quantity), INVENTORY_TRANSACTION.SELL_ON_CASH);
    }

    @PutMapping("/sellOnCredit/{id}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public String sellOnCredit(@PathVariable String id, @PathVariable double quantity){
        return productService.updateStockQuantityOfProduct(id, (0-quantity), INVENTORY_TRANSACTION.SELL_ON_CREDIT);
    }

    @PutMapping("/buyOnCredit/{id}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public String buyOnCredit(@PathVariable String id, @PathVariable double quantity){
        return productService.updateStockQuantityOfProduct(id, (quantity), INVENTORY_TRANSACTION.BUY_ON_CREDIT);
    }

    @PutMapping("/buyOnCash/{id}/{quantity}")
    @ResponseStatus(HttpStatus.CREATED)
    public String buyOnCash(@PathVariable String id, @PathVariable double quantity){
        return productService.updateStockQuantityOfProduct(id, (quantity), INVENTORY_TRANSACTION.BUY_ON_CASH);
    }

    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProductsByCategory(@PathVariable String id){
        return productService.getProductsByCategory(id);
    }
}
