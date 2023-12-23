package com.flashmart.inventory.dto;

import com.flashmart.inventory.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryProductResponse {

    private String categoryId;
    private String name;
    private String icon;
    private List<ProductResponse> productList;
}
