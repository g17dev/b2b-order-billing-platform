package org.portfolio.infrestructure.order;

import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderRepository;
import org.portfolio.domain.product.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository {
    private final Map<Integer, Order> storage = new HashMap<>();

    @Override
    public int getLastOrderId() {
        int id = 1;
        if(storage.containsKey(1)){
            id = storage.size();
            id++;
        }
        return id;
    }

    @Override
    public boolean save(Order order) {
        storage.put(order.getIdOrder(), order);
        return true;
    }

    @Override
    public Order findById(int id) {
        if (storage.containsKey(id)) {
            return storage.get(id);
        }
        else throw new RuntimeException("Order not found");
    }

    @Override
    public Order updateOrder(int idOrder, List<Product> products) {
        Order orderUpdated = storage.get(idOrder);
        orderUpdated.updateOrder(products);
        storage.put(idOrder, orderUpdated);
        return orderUpdated;
    }
}