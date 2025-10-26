package io.eddie.demo.domain.orders.application.port.out;

import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import org.hibernate.query.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderPersistencePort {
    void save(Orders order);

    Optional<Orders> findByCode(String code);

    Optional<Orders> findByAccountCodeAndCode(String accountCode, String code);

    Page<Orders> findAllByAccountCode(String accountCode, Pageable pageable);

}
