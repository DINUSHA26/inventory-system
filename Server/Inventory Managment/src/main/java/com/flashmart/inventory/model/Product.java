package com.flashmart.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String name;
    private String icon;
    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;
    private int measureType;
    private double unitPrice;
    private double stockQuantity;
    private double bufferQuantity;
}
