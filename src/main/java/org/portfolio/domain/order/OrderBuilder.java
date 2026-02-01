package org.portfolio.domain.order;

import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.product.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderBuilder {

    private int idOrder;
    private OrderStatus orderStatus;
    private LocalDate createdAt = LocalDate.now();
    private List<Product> products = new ArrayList<>();
    private Customer customer;
    private BigDecimal total;

    public OrderBuilder setId(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("Id cannot be 0 or negative");
        }
        else this.idOrder = id;
        return this;
    }

    public OrderBuilder setProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be empty");
        }
        this.products.addAll(products); // Asegúrate de que esta lista sea inicializada
        return this;
    }

    public OrderBuilder setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        else this.customer = customer;
        return this;
    }

    private BigDecimal calculateTotal(List<Product> products) {
        if (products.isEmpty()) {
            throw new IllegalArgumentException("Products cannot be empty");
        }
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order build() {
        if (idOrder <= 0) {
            throw new IllegalStateException("Id must be defined");
        }
        Objects.requireNonNull(customer, "Customer is required");
        Objects.requireNonNull(products, "Products are required");
        total = calculateTotal(products);

        return new Order(
            idOrder,
            OrderStatus.PENDING, // estado inicial controlado
            createdAt, // Estado inicial controlado
            products,
            customer,
            total
        );
    }
}
