package org.portfolio.domain.order;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.customer.CustomerBuilder;
import org.portfolio.domain.product.Product;
import org.portfolio.domain.product.ProductBuilder;
import org.portfolio.domain.product.ProductCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OrderTest {

    private static List<Product> products = Arrays.asList(

        new ProductBuilder()
                .setId(1)
                .setTitle("iPhone 17 Pro")
                .setDescription("256GB de almacenamiento")
                .setCategory(ProductCategory.ELECTRONICS)
                .setStock(10)
                .setPrice(new BigDecimal("20000"))
            .build(),

        new ProductBuilder()
                .setId(2)
                .setTitle("MacBook Air M1")
                .setDescription("256GB de almacenamiento")
                .setCategory(ProductCategory.ELECTRONICS)
                .setStock(15)
                .setPrice(new BigDecimal("13000"))
            .build(),

        new ProductBuilder()
                .setId(3)
                .setTitle("Magic Keyboard")
                .setDescription("Magic Keyboard with Touch ID")
                .setCategory(ProductCategory.ELECTRONICS)
                .setStock(20)
                .setPrice(new BigDecimal("2000"))
            .build()
    );

    private static Customer customer = new CustomerBuilder()
            .setId(1)
            .setFirstName("Jacob")
            .setLastName("Gonzalez")
            .setAge(24)
            .setPhoneNumber("+52 6878576301")
            .setEmail("glzj@proton.me")
            .setAddress("Calle Benito Juarez, 289, 81175, Ejido El Progreso, Guasave, Sinaloa, Mexico")
            .Build();

    @Test
    public void testCreateOrder() {
        Order order = new OrderBuilder()
                .setId(1)
                .setProducts(products)
                .setCustomer(customer)
                .build();

        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(new BigDecimal("35000"), order.getTotal());
    }

    @Test
    public void testCreateOrderWithoutProducts() {
        List<Product> products1 = new ArrayList<>();
        OrderBuilder order = new OrderBuilder();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
            () -> order
                    .setId(1)
                    .setCustomer(customer)
                    .setProducts(products1)
                    .build()
        );
        assertEquals("Products cannot be empty", exception.getMessage());
    }

    @Test
    public void testUpdateOrder() {
        List<Product> newProducts = Arrays.asList(
            new ProductBuilder()
                    .setId(1)
                    .setTitle("iPhone 17 Pro")
                    .setDescription("256GB de almacenamiento")
                    .setCategory(ProductCategory.ELECTRONICS)
                    .setStock(10)
                    .setPrice(new BigDecimal("20000"))
                    .build(),

            new ProductBuilder()
                    .setId(2)
                    .setTitle("MacBook Air M1")
                    .setDescription("256GB de almacenamiento")
                    .setCategory(ProductCategory.ELECTRONICS)
                    .setStock(15)
                    .setPrice(new BigDecimal("13000"))
                    .build()
        );
        Order order = new OrderBuilder()
                .setId(1)
                .setProducts(products)
                .setCustomer(customer)
                .build();

        order.updateOrder(newProducts);

        assertEquals(1, order.getIdOrder());
        assertEquals(2, order.getProducts().size());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(new BigDecimal("33000"), order.getTotal());
    }

    @Test
    public void testUpdateOrderWithoutProducts() {
        List<Product> newProducts = Arrays.asList();
        Order order = new OrderBuilder()
                .setId(1)
                .setProducts(products)
                .setCustomer(customer)
                .build();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> order.updateOrder(newProducts)
        );

        assertEquals("Products cannot be empty", exception.getMessage());
    }

    @Test
    public void testExceptionWhenUpdatingOrderThatIsNotPending() {
        List<Product> newProducts = Arrays.asList(
                new ProductBuilder()
                    .setId(1)
                    .setTitle("iPhone 17 Pro")
                    .setDescription("256GB de almacenamiento")
                    .setCategory(ProductCategory.ELECTRONICS)
                    .setStock(10)
                    .setPrice(new BigDecimal("20000"))
                    .build()
        );
        Order order = new OrderBuilder()
                .setId(1)
                .setProducts(products)
                .setCustomer(customer)
                .build();

        order.payOrder();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> order.updateOrder(newProducts)
        );

        assertEquals("Order status must be PENDING", exception.getMessage());
    }

    @Test
    public void testPayOrder() {
        Order order = new OrderBuilder()
            .setId(1)
            .setProducts(products)
            .setCustomer(customer)
            .build();

        order.payOrder();

        assertEquals(OrderStatus.PAID, order.getOrderStatus());
        assertEquals(new BigDecimal("35000"), order.getTotal());
    }

    @Test
    public void testExceptionWhenPayOrderThatIsNotPending() {
        Order order = new OrderBuilder()
            .setId(1)
            .setProducts(products)
            .setCustomer(customer)
            .build();

        order.payOrder();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> order.payOrder()
        );

        assertEquals("Only pending orders can be paid", exception.getMessage());
    }

    @Test
    public void testCancelOrder() {
        Order order = new OrderBuilder()
            .setId(1)
            .setProducts(products)
            .setCustomer(customer)
            .build();

        order.cancelOrder();

        assertEquals(OrderStatus.CANCELED, order.getOrderStatus());
    }
}