package com.flashmart.inventory.repository;

import com.flashmart.inventory.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, String> {
}
