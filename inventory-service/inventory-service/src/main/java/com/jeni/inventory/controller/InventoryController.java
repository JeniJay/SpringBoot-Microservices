package com.jeni.inventory.controller;

import com.jeni.inventory.dto.InventoryRequest;
import com.jeni.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private boolean isInStock(@RequestParam("skuCode")String skuCode,@RequestParam("quantity")int quantity)
    {
        return inventoryService.checkInventory(skuCode,quantity);
    }
}
