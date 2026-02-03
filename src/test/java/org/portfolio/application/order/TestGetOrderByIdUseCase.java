package org.portfolio.application.order;

import org.junit.jupiter.api.Test;
import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.customer.CustomerBuilder;
import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderRepository;
import org.portfolio.domain.product.Product;
import org.portfolio.domain.product.ProductBuilder;
import org.portfolio.domain.product.ProductCategory;
import org.portfolio.infrestructure.order.InMemoryOrderRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestGetOrderByIdUseCase {

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
                    .setId(1)
                    .setTitle("MacBook Air M1")
                    .setDescription("256GB de almacenamiento")
                    .setCategory(ProductCategory.ELECTRONICS)
                    .setStock(15)
                    .setPrice(new BigDecimal("13000"))
                    .build(),

            new ProductBuilder()
                    .setId(1)
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

    private final OrderRepository orderRepository = new InMemoryOrderRepository();

    @Test
    public void testFindById() {
        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderRepository);
        createOrderUseCase.execute(customer, products);
        createOrderUseCase.execute(customer, products);
        createOrderUseCase.execute(customer, products);
        createOrderUseCase.execute(customer, products);

        GetOrderByIdUseCase orderByIdUseCase = new GetOrderByIdUseCase(orderRepository);
        Order foundOrder = orderByIdUseCase.execute(3);

        assertEquals(3, foundOrder.getIdOrder());
    }

    @Test
    public void testFindByIdNotFound() {
        CreateOrderUseCase createOrderUseCase = new CreateOrderUseCase(orderRepository);
        createOrderUseCase.execute(customer, products);

        GetOrderByIdUseCase orderByIdUseCase = new GetOrderByIdUseCase(orderRepository);

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderByIdUseCase.execute(2)
        );

        assertEquals("Order not found", exception.getMessage());
    }
}
