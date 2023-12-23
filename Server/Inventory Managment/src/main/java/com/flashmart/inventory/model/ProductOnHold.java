package com.flashmart.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductOnHold {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String holdId;
    @ManyToOne(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId", nullable = false)
    private Product product;
    private double quantity;
    private Date holdTime;
}
