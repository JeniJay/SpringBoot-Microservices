package com.jeni.inventory.service;

import com.jeni.inventory.dto.InventoryRequest;
import com.jeni.inventory.model.Inventory;
import com.jeni.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean checkInventory(String skuCode, int quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(
                skuCode,quantity);
    }
}
