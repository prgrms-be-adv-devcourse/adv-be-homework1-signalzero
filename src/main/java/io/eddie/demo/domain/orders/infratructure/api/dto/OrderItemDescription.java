package io.eddie.demo.domain.orders.infratructure.api.dto;

public record OrderItemDescription(
        String productCode,
        String productName,
        Long productPrice,
        Integer quantity) {
}
