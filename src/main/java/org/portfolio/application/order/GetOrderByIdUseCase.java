package org.portfolio.application.order;

import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderRepository;

public class GetOrderByIdUseCase {

    private final OrderRepository orderRepository;
    public GetOrderByIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(int idOrder) {
        return orderRepository.findById(idOrder);
    }
}
