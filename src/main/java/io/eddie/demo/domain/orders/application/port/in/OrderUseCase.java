package io.eddie.demo.domain.orders.application.port.in;

import io.eddie.demo.domain.orders.domain.model.vo.CreateOrderRequest;
import io.eddie.demo.domain.orders.domain.model.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderUseCase {

    Orders createOrder(String accountCode, CreateOrderRequest request);

    Orders getOrder(String accountCode, String orderCode);

    Orders getOrderByCode(String code);

    Orders completeOrder(String accountCode, String orderCode);
    Orders cancelOrder(String accountCode, String orderCode);

    Page<Orders> getOrders(String accountCode, Pageable pageable);

}
