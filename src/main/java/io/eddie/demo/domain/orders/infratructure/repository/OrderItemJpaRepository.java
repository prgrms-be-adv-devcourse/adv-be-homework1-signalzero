package io.eddie.demo.domain.orders.infratructure.repository;

import io.eddie.demo.domain.orders.domain.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItem, Long> {

}
