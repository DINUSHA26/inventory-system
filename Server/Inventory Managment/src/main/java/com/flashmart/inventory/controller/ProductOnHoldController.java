package com.flashmart.inventory.controller;

import com.flashmart.inventory.dto.ProductOnHoldRequest;
import com.flashmart.inventory.service.ProductOnHoldService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
@RequiredArgsConstructor
@EnableScheduling
public class ProductOnHoldController {

    private final ProductOnHoldService productOnHoldService;
    @PutMapping("/onHold")
    @ResponseStatus(HttpStatus.CREATED)
    public String productToHold(@RequestBody List<ProductOnHoldRequest> requestList){
        return productOnHoldService.toOnHold(requestList);
    }
}
