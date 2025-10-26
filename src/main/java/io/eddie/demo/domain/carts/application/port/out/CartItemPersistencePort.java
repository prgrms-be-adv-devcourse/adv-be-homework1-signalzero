package io.eddie.demo.domain.carts.application.port.out;

import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.domain.model.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface CartItemPersistencePort {
    List<CartItem> findByCart(Cart cart);
    Optional<CartItem> findOwnCartItem(String accountCode, String cartItemCode);
    List<CartItem> findAllByCodesIn(List<String> cartItemCodes);
    CartItem save(CartItem cartItem);
    void delete(CartItem targetItem);
    void deleteAll(List<CartItem> itemsByCodes);
}
