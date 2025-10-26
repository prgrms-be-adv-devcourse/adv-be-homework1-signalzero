package io.eddie.demo.domain.carts.infrastructure.repository;

import io.eddie.demo.domain.carts.application.port.out.CartPersistencePort;
import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartRepository implements CartPersistencePort {

    private final CartJpaRepository cartJpaRepository;

    @Override
    public Cart save(Cart cart) {
        return cartJpaRepository.save(cart);
    }

    @Override
    public Optional<Cart> findByAccountCode(String accountCode) {
        return cartJpaRepository.findByAccountCode(accountCode);
    }
}
