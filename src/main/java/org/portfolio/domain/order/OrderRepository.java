package org.portfolio.domain.order;

import org.portfolio.domain.product.Product;

import java.util.List;

public interface OrderRepository {

    public int getLastOrderId();

    public boolean save(Order order);

    public Order findById(int id);

    public Order updateOrder(int idOrder, List<Product> products);
}