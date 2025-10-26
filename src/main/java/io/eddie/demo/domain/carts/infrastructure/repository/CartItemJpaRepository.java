package io.eddie.demo.domain.carts.infrastructure.repository;

import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.domain.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCart(Cart cart);

    @Query("""
    select
        ci
    from
        CartItem ci
    join fetch
        ci.cart c
    where
        c.accountCode = :accountCode
    and
        ci.code = :cartItemCode
    """)
    Optional<CartItem> findOwnCartItem(String accountCode, String cartItemCode);

    @Query("""
    select
        ci
    from
        CartItem ci
    where
        ci.code in :cartItemCodes
    """)
    List<CartItem> findAllByCodesIn(List<String> cartItemCodes);

}
