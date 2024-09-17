package com.jeni.inventory.repository;

import com.jeni.inventory.model.Inventory;
import com.jeni.inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory,String> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);

}
