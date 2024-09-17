package com.jeni.product.dto;

import java.math.BigDecimal;

public record ResponseDTO(String id, String name, BigDecimal price) {
}
