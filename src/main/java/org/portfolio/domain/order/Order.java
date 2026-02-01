package org.portfolio.domain.order;

import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int idOrder;
    private OrderStatus orderStatus;
    private final LocalDate createdAt;
    private List<Product> products = new ArrayList<>();
    private final Customer customer;
    private BigDecimal total;

    public Order(
            int idOrder,
            OrderStatus orderStatus,
            LocalDate createdAt,
            List<Product> products,
            Customer customer,
            BigDecimal total) {
        this.idOrder = idOrder;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.products = products;
        this.customer = customer;
        this.total = total;
    }

    // Actualizar Orden
    public Order updateOrder(List<Product> newProducts) {
        if(this.orderStatus == OrderStatus.PAID || this.orderStatus == OrderStatus.SHIPPED) {
            throw new IllegalArgumentException("Order status must be PENDING");
        }
        this.products.addAll(newProducts);
        recalculateTotal(newProducts);
        return this;
    }

    // Pagar Orden
    public boolean payOrder() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending orders can be paid");
        }
        this.orderStatus = OrderStatus.PAID;
        return true;
    }

    // Cancelar Orden
    public boolean cancelOrder() {
        if (orderStatus == OrderStatus.COMPLETED || orderStatus == OrderStatus.CANCELED) {
            throw new IllegalStateException("Cannot cancel orders completed");
        }
        this.orderStatus = OrderStatus.CANCELED;
        return true;
    }

    private BigDecimal recalculateTotal(List<Product> products) {
        return this.total = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters
    public int getIdOrder() {
        return idOrder;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public List<Product> getProducts() {
        return products;
    }
    public Customer getCustomer() {
        return customer;
    }
    public BigDecimal getTotal() {
        return total;
    }
}
