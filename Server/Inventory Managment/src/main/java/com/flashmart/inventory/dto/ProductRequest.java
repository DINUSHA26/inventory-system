package com.flashmart.inventory.dto;

import com.flashmart.inventory.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {

    private String name;
    private String icon;

    private String categoryId;
    private int measureType;
    private double unitPrice;
    private double stockQuantity;
    private double bufferQuantity;
}
