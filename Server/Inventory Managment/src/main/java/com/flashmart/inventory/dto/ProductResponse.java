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
public class ProductResponse {

    private String productId;
    private String name;
    private String icon;

    private Category category;
    private String measureType;
    private double unitPrice;
    private double stockQuantity;
    private double bufferQuantity;
}
