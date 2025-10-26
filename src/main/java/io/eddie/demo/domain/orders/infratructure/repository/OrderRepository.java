package io.eddie.demo.domain.orders.infratructure.repository;

import io.eddie.demo.domain.orders.application.port.out.OrderPersistencePort;
import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements OrderPersistencePort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public void save(Orders order) {
        orderJpaRepository.save(order);
    }

    @Override
    public Optional<Orders> findByCode(String code) {
        return orderJpaRepository.findByCode(code);
    }

    @Override
    public Optional<Orders> findByAccountCodeAndCode(String accountCode, String code) {
        return orderJpaRepository.findByAccountCodeAndCode(accountCode, code);
    }

    @Override
    public Page<Orders> findAllByAccountCode(String accountCode, Pageable pageable) {
        return orderJpaRepository.findAllByAccountCode(accountCode, pageable);
    }

}
