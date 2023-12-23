package com.flashmart.inventory.service;

import com.flashmart.inventory.consts.INVENTORY_TRANSACTION;
import com.flashmart.inventory.consts.MEASURE_TYPE;
import com.flashmart.inventory.dto.InventoryMovementRequest;
import com.flashmart.inventory.dto.InventoryMovementResponse;
import com.flashmart.inventory.dto.ProductResponse;
import com.flashmart.inventory.model.InventoryMovement;
import com.flashmart.inventory.model.Product;
import com.flashmart.inventory.repository.InventoryMovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryMovementService {
    private final InventoryMovementRepository inventoryMovementRepository;

    public List<InventoryMovementResponse> getAllTransactions(){
        List<InventoryMovement> list = inventoryMovementRepository.findAll();
        return list.stream().map(this::mapToInventoryResponse).toList();
    }

    private InventoryMovementResponse mapToInventoryResponse(InventoryMovement inventory){
        return InventoryMovementResponse.builder()
                .type(INVENTORY_TRANSACTION.ToMessage(inventory.getType()))
                .id(inventory.getId())
                .movementDate(inventory.getMovementDate())
                .product(mapToProductResponse(inventory.getProduct()))
                .quantity(inventory.getQuantity())
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
