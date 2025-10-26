package io.eddie.demo.domain.carts.infrastructure.api.dto;

import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.domain.model.entity.CartItem;

public class CartMapper {

    public static CartDescription toCartDescription(Cart cart) {
        return new CartDescription(
                cart.getCode(),
                cart.getCartItems()
                        .stream()
                        .map(CartMapper::toCartItemDescription)
                        .toList(),
                cart.getTotalPrice()
        );
    }

    public static CartItemDescription toCartItemDescription(CartItem cartItem) {
        return new CartItemDescription(
                cartItem.getCode(),
                cartItem.getProductCode(),
                cartItem.getProductName(),
                cartItem.getProductPrice(),
                cartItem.getQuantity(),
                cartItem.getCreatedAt()
        );
    }

}
