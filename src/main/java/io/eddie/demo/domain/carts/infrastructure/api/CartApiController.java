package io.eddie.demo.domain.carts.infrastructure.api;

import io.eddie.demo.common.model.web.BaseResponse;
import io.eddie.demo.domain.carts.infrastructure.api.dto.CartDescription;
import io.eddie.demo.domain.carts.infrastructure.api.dto.CartItemDescription;
import io.eddie.demo.domain.carts.domain.model.entity.Cart;
import io.eddie.demo.domain.carts.domain.model.entity.CartItem;
import io.eddie.demo.domain.carts.domain.model.vo.CreateCartItemRequest;
import io.eddie.demo.domain.carts.application.port.in.CartUseCase;
import io.eddie.demo.domain.carts.infrastructure.api.dto.CartMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartApiController {

    private final CartUseCase cartUseCase;

    @GetMapping
    public BaseResponse<CartDescription> getDescription(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode
    ) {
        Cart cart = cartUseCase.getByAccountCode(accountCode);
        return new BaseResponse<>(
                CartMapper.toCartDescription(cart),
                "장바구니를 성공적으로 조회하였습니다"
        );
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse<CartItemDescription> appendItem(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestBody @Valid CreateCartItemRequest request
    ) {
        CartItem cartItem = cartUseCase.appendItem(accountCode, request);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니에 항목이 성공적으로 추가되었습니다."
        );
    }

    @DeleteMapping("/items/{itemCode}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<Void> deleteItem(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @PathVariable String itemCode
    ) {
        cartUseCase.deleteItem(accountCode, itemCode);
        return new BaseResponse<>(
                null,
                "장바구니에 항목이 성공적으로 삭제되었습니다."
        );
    }

    @PatchMapping("/items/{itemCode}/increase")
    public BaseResponse<CartItemDescription> increaseQuantity(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @PathVariable String itemCode
    ) {
        CartItem cartItem = cartUseCase.increaseQuantity(accountCode, itemCode);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니 항목 갯수가 성공적으로 증가하였습니다."
        );
    }

    @PatchMapping("/items/{itemCode}/decrease")
    public BaseResponse<CartItemDescription> decreaseQuantity(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @PathVariable String itemCode
    ) {
        CartItem cartItem = cartUseCase.decreaseQuantity(accountCode, itemCode);
        return new BaseResponse<>(
                CartMapper.toCartItemDescription(cartItem),
                "장바구니 항목 갯수가 성공적으로 감소되었습니다."
        );
    }




}
