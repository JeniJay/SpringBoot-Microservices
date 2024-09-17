package com.jeni.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderPlacedEvent {
    private String orderId;
    private String email;
    private String firstName;
    private String lastName;


}
