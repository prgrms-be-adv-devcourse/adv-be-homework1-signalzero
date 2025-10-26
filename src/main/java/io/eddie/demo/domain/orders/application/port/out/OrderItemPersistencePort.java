package io.eddie.demo.domain.orders.application.port.out;

import io.eddie.demo.domain.orders.domain.model.entity.OrderItem;

import java.util.List;

public interface OrderItemPersistencePort {
    void saveAll(List<OrderItem> orderItems);
}
