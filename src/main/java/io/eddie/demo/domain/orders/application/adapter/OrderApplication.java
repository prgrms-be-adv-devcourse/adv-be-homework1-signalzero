package io.eddie.demo.domain.orders.application.adapter;

import io.eddie.demo.domain.carts.domain.model.entity.CartItem;
import io.eddie.demo.domain.carts.application.port.in.CartUseCase;
import io.eddie.demo.domain.orders.application.port.in.OrderUseCase;
import io.eddie.demo.domain.orders.application.port.out.OrderItemPersistencePort;
import io.eddie.demo.domain.orders.application.port.out.OrderPersistencePort;
import io.eddie.demo.domain.orders.domain.model.entity.OrderItem;
import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import io.eddie.demo.domain.orders.domain.model.vo.CreateOrderRequest;
import io.eddie.demo.domain.orders.domain.model.vo.OrderState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderApplication implements OrderUseCase {

    private final OrderPersistencePort orderPersistencePort;
    private final OrderItemPersistencePort orderItemPersistencePort;
    private final CartUseCase cartUseCase;

    @Override
    @Transactional
    public Orders createOrder(String accountCode, CreateOrderRequest request) {

        Orders order = Orders.builder()
                .accountCode(accountCode)
                .build();

        List<CartItem> targetItems = cartUseCase.getItemsByCodes(request.cartItemCodes());

        if ( targetItems.isEmpty() )
            throw new IllegalStateException("선택된 아이템이 없습니다");

        List<OrderItem> orderItems = targetItems.stream()
                .map(i -> {
                    OrderItem item = OrderItem.builder()
                            .productCode(i.getProductCode())
                            .productName(i.getProductName())
                            .productPrice(i.getProductPrice())
                            .quantity(i.getQuantity())
                            .build();

                    order.addItem(item);
                    return item;
                })
                .toList();

        orderPersistencePort.save(order);

        orderItemPersistencePort.saveAll(orderItems);
        cartUseCase.deleteItemsByCodes(request.cartItemCodes());

        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public Orders getOrder(String accountCode, String orderCode) {
        return orderPersistencePort.findByAccountCodeAndCode(accountCode, orderCode)
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
    }

    @Override
    public Orders getOrderByCode(String code) {
        return orderPersistencePort.findByCode(code)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public Orders completeOrder(String accountCode, String orderCode) {

        Orders targetOrder = getOrder(accountCode, orderCode);
        targetOrder.complete();

        return targetOrder;
    }

    @Override
    @Transactional
    public Orders cancelOrder(String accountCode, String orderCode) {

        Orders order = getOrder(accountCode, orderCode);

        if ( order.getOrderState() == OrderState.CANCELLED ) {
            throw new IllegalStateException("이미 취소된 주문입니다.");
        }

//        Optional<Payment> paymentOptional = paymentApplication.findPaymentByOrderCode(code);
//
//        if (paymentOptional.isEmpty()) {
//            order.cancel();
//            return new OrderCodeResponse(code);
//        }
//
//        Payment payment = paymentOptional.get();
//
//        // 결제가 완료된 상태라면
//        if ( payment.getPaymentStatus() == Payment.PaymentStatus.PAYMENT_COMPLETED ) {
//
//            // 결제일자로부터 3일 이전 까지는 취소 가능
//            if ( payment.getCreatedAt().plusDays(3).isAfter(LocalDateTime.now())) {
//                paymentApplication.refundToDeposit(new PaymentRequest(code, calcTotalPrice(getOrderItemDescriptions(order))));
//                order.cancel();
//                return new OrderCodeResponse(code);
//            }
//
//        }
//
//        if ( payment.getPaymentStatus() == Payment.PaymentStatus.PAYMENT_READY ) {
//            // 결제가 아직 발생하지 않았다면
//            order.cancel();
//            return new OrderCodeResponse(code);
//        }
//
//        throw new InvalidOrderRequestException("취소할 수 없는 상태의 주문입니다.");

        return order;

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Orders> getOrders(String accountCode, Pageable pageable) {
        return orderPersistencePort.findAllByAccountCode(accountCode, pageable);
    }

}

