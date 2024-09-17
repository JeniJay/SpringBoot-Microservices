package com.jeni.order.client;

import groovy.util.logging.Slf4j;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@Slf4j
//@FeignClient(value = "inventory", url="${inventoryservice.port}")
public interface InventoryClient
{
    Logger log= LoggerFactory.getLogger(InventoryClient.class);
    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory", fallbackMethod = "isInStockFallback")
    boolean isInStock(@RequestParam String skuCode,@RequestParam Integer quantity) ;

    default boolean isInStockFallback(String skuCode, Integer quantity, Throwable e)
    {
        System.out.println("Fallback method called for skuCode: " + skuCode + " and quantity: " + quantity + " for the reason " + e.getMessage());
        log.info("Fallback method called for skuCode: {} and quantity: {} for the reason {}", skuCode, quantity,e.getMessage());
        return false;
    }


}
