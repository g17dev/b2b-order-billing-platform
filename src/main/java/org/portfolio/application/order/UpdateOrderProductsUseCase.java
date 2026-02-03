package org.portfolio.application.order;

import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderRepository;
import org.portfolio.domain.product.Product;

import java.util.List;

public class UpdateOrderProductsUseCase {

    private final OrderRepository orderRepository;
    public UpdateOrderProductsUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean execute(int idOrder, List<Product> products) {
        Order order = orderRepository.updateOrder(idOrder, products);
        return orderRepository.save(order);
    }
}
