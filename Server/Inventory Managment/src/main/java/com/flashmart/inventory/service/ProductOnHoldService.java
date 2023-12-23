package com.flashmart.inventory.service;

import com.flashmart.inventory.consts.INVENTORY_TRANSACTION;
import com.flashmart.inventory.dto.ProductOnHoldRequest;
import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.model.ProductOnHold;
import com.flashmart.inventory.repository.ProductOnHoldRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductOnHoldService {

    private final ProductOnHoldRepository productOnHoldRepository;
    private final ProductService productService;

    @Transactional
    public String toOnHold(List<ProductOnHoldRequest> requestList){

            for (ProductOnHoldRequest request: requestList){
                ProductOnHold onHold = ProductOnHold.builder()
                        .product(productService.getProductforHold(request.getProductId()))
                        .holdTime(new Date())
                        .quantity(request.getQuantity())
                        .build();

                Product product = onHold.getProduct();
                if(product.getStockQuantity()-onHold.getQuantity()>= product.getBufferQuantity()){
                    productService.updateStockQuantityOfProduct(product.getProductId(),0-onHold.getQuantity(), INVENTORY_TRANSACTION.ONHOLD);
                    productOnHoldRepository.save(onHold);

                }else {
                    throw new RuntimeException("Stock quantity cannot be lesser than buffer quantity");
                }

            }


        return "Products has been on hold";

    }
    @Scheduled(fixedRate = 200000)
    @Transactional
    public void reClaim(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -5);
        Date cutoffTime = calendar.getTime();
        List<ProductOnHold> onHoldList = productOnHoldRepository.findByHoldTimeBefore(cutoffTime);
        for (ProductOnHold onHold: onHoldList){
            productService.updateStockQuantityOfProduct(onHold.getProduct().getProductId(),onHold.getQuantity(),INVENTORY_TRANSACTION.RECLAIM);
        }
        productOnHoldRepository.deleteAllInBatch(onHoldList);
    }


}
