package io.eddie.demo.domain.carts.infrastructure.api.dto;

import java.time.LocalDateTime;

public record CartItemDescription(
        String code,
        String productCode,
        String productName,
        Long productPrice,
        Integer quantity,
        LocalDateTime appendedAt
) {
}
