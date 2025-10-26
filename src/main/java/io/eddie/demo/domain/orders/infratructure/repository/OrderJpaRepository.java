package io.eddie.demo.domain.orders.infratructure.repository;

import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByCode(String code);

    Optional<Orders> findByAccountCodeAndCode(String accountCode, String code);

    Page<Orders> findAllByAccountCode(String accountCode, Pageable pageable);

}
