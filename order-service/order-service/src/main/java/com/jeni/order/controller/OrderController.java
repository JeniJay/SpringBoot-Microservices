package com.jeni.order.controller;

import com.jeni.order.dto.OrderRequest;
import com.jeni.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private String createOrder(@RequestBody OrderRequest orderRequest)
    {
        return orderService.createOrder(orderRequest);
    }
}
