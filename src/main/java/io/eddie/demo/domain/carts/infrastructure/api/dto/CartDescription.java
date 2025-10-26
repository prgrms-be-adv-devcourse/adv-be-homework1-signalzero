package io.eddie.demo.domain.carts.infrastructure.api.dto;

import java.util.List;

public record CartDescription(
        String code,
        List<CartItemDescription> items,
        Long totalPrice
) {
}
