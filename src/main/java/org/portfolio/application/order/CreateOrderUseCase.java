package org.portfolio.application.order;

import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderBuilder;
import org.portfolio.domain.order.OrderRepository;
import org.portfolio.domain.product.Product;

import java.util.List;

public class CreateOrderUseCase {

    private final OrderRepository orderRepository;
    public CreateOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order execute(Customer customer, List<Product> products) {
        Order order = new OrderBuilder()
                .setId(orderRepository.getLastOrderId())
                .setCustomer(customer)
                .setProducts(products)
                .build();
        if(orderRepository.save(order)){
            return order;
        }
        return null;
    }
}