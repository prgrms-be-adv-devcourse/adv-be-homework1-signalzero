package io.eddie.demo.domain.carts.infrastructure.repository;

import io.eddie.demo.domain.carts.application.port.out.CartItemPersistencePort;
import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.domain.model.entity.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CartItemRepository implements CartItemPersistencePort {

    private final CartItemJpaRepository cartItemJpaRepository;

    @Override
    public List<CartItem> findByCart(Cart cart) {
        return cartItemJpaRepository.findByCart(cart);
    }

    @Override
    public Optional<CartItem> findOwnCartItem(String accountCode, String cartItemCode) {
        return cartItemJpaRepository.findOwnCartItem(accountCode, cartItemCode);
    }

    @Override
    public List<CartItem> findAllByCodesIn(List<String> cartItemCodes) {
        return cartItemJpaRepository.findAllByCodesIn(cartItemCodes);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemJpaRepository.save(cartItem);
    }

    public void delete(CartItem targetItem) {
        cartItemJpaRepository.delete(targetItem);
    }
    @Override
    public void deleteAll(List<CartItem> itemsByCodes) {
        cartItemJpaRepository.deleteAll(itemsByCodes);
    }
}
