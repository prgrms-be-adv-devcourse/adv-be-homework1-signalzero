package io.eddie.demo.domain.orders.infratructure.repository;

import io.eddie.demo.domain.orders.application.port.out.OrderItemPersistencePort;
import io.eddie.demo.domain.orders.domain.model.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository implements OrderItemPersistencePort {

    private final OrderItemJpaRepository orderItemJpaRepository;


    @Override
    public void saveAll(List<OrderItem> orderItems) {
        orderItemJpaRepository.saveAll(orderItems);
    }
}
