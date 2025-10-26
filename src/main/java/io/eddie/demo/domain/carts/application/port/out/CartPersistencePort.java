package io.eddie.demo.domain.carts.application.port.out;

import io.eddie.demo.domain.carts.domain.model.entity.Cart;

import java.util.Optional;

public interface CartPersistencePort {
    Cart save(Cart cart);
    Optional<Cart> findByAccountCode(String accountCode);
}
