package com.flashmart.inventory.controller;

import com.flashmart.inventory.dto.InventoryMovementResponse;
import com.flashmart.inventory.repository.InventoryMovementRepository;
import com.flashmart.inventory.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/transaction")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryMovementResponse> getAllTransactions(){
        return inventoryMovementService.getAllTransactions();
    }
}
