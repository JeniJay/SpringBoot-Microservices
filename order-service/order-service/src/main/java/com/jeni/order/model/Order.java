package com.jeni.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="Order")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    String id;
    String skuCode;
    String name;
    int quantity;
}
