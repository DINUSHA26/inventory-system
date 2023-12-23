package com.flashmart.inventory.dto;

import com.flashmart.inventory.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InventoryMovementRequest {

    private String productId;
    private int type;
    private double quantity;
    private Date movementDate;
}
