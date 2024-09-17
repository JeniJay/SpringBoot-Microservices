package com.jeni.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="inventory")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    String id;
    String skuCode;
    int quantity;
}
