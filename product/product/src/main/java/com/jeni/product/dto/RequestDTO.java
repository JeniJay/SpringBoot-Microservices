package com.jeni.product.dto;

import java.math.BigDecimal;

public record RequestDTO(String id, String name, BigDecimal price) {
}
