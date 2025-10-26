package io.eddie.demo.domain.carts.infrastructure.repository;

import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByAccountCode(String accountCode);

}
