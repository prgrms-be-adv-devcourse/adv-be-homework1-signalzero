package io.eddie.demo.domain.orders.infratructure.api.dto;

import io.eddie.demo.domain.orders.domain.model.entity.OrderItem;
import io.eddie.demo.domain.orders.domain.model.entity.Orders;

public class OrderMapper {

    public static OrderDescription toOrderDescription(Orders order) {
        return new OrderDescription(
                order.getCode(),
                order.getOrderState(),
                order.getOrderItems()
                        .stream()
                        .map(OrderMapper::toOrderItemDescription)
                        .toList(),
                order.getCreatedAt(),
                order.getTotalPrice()
        );
    }

    public static OrderItemDescription toOrderItemDescription(OrderItem orderItem) {
        return new OrderItemDescription(
                orderItem.getProductCode(),
                orderItem.getProductName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity()
        );
    }

}
