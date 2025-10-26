package io.eddie.demo.domain.orders.domain.model.vo;

import java.util.List;

public record CreateOrderRequest(
        List<String> cartItemCodes
) {
}
