package io.eddie.demo.domain.orders.infratructure.api;

import io.eddie.demo.common.model.web.BaseResponse;
import io.eddie.demo.domain.orders.domain.model.OrderMapper;
import io.eddie.demo.domain.orders.domain.model.dto.OrderDescription;
import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import io.eddie.demo.domain.orders.domain.model.vo.CreateOrderRequest;
import io.eddie.demo.domain.orders.application.port.in.OrderUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderApiController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    public BaseResponse<OrderDescription> create(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @RequestBody @Valid CreateOrderRequest request
    ) {

        Orders order = orderUseCase.createOrder(accountCode, request);

        return new BaseResponse<>(
                OrderMapper.toOrderDescription(order),
                "성공적으로 주문되었습니다."
        );

    }

    @GetMapping("/{orderCode}")
    public BaseResponse<OrderDescription> getDescription(
            @AuthenticationPrincipal(expression = "accountCode") String accountCode,
            @PathVariable String orderCode
    ) {

        Orders order = orderUseCase.getOrder(accountCode, orderCode);

        return new BaseResponse<>(
                OrderMapper.toOrderDescription(order),
                "주문이 성공적으로 조회되었습니다."
        );

    }


}
