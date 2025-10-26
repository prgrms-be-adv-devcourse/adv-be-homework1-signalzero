package io.eddie.demo.domain.carts.domain.model.dto;

import java.util.List;

public record CartDescription(
        String code,
        List<CartItemDescription> items,
        Long totalPrice
) {
}
