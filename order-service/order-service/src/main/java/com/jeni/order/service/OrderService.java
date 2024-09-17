package com.jeni.order.service;


import com.jeni.order.client.InventoryClient;
import com.jeni.order.dto.OrderRequest;
import com.jeni.order.event.OrderPlacedEvent;
import com.jeni.order.model.Order;
import com.jeni.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<Object, OrderRequest> kafkaTemplate;

    @Autowired
    private InventoryClient inventoryClient;
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String createOrder(OrderRequest orderRequest) {
        if(inventoryClient.isInStock(orderRequest.skuCode(),orderRequest.quantity())) {
            Order order = Order.builder().name(orderRequest.name())
                    .skuCode(orderRequest.skuCode())
                    .quantity(orderRequest.quantity()).build();
            orderRepository.save(order);
            log.info("Order Placed Successfully");

            // Send the message to Kafka Topic
            log.info("Sending OrderPlacedEvent to Kafka topic order-placed");
            orderRequest=orderRequest.withId(order.getId());
//            OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.builder()
//                    .orderId(order.getId())
//                            .email(orderRequest.userDetails().email())
//                            .firstName(orderRequest.userDetails().firstName())
//                            .lastName(orderRequest.userDetails().lastName())
//                            .build();
//            kafkaTemplate.send("order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderRequest);
            log.info("OrderPlacedEvent sent to Kafka topic order-placed");

            return "Order Placed Successfully";
        }
        else {
            throw new RuntimeException("Product with SkuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }

    public String fallbackMethod(OrderRequest orderRequest, Throwable e) {
        log.info("Fallback method called for skuCode: {} and quantity: {} for the reason {}", orderRequest.skuCode(), orderRequest.quantity(), e.getMessage());
        return "Fallback method called for skuCode: " + orderRequest.skuCode() + " and quantity: " + orderRequest.quantity() + " for the reason " + e.getMessage();
    }
}
