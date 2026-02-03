package org.portfolio.application.order;

import org.junit.jupiter.api.Test;
import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.customer.CustomerBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.portfolio.domain.order.Order;
import org.portfolio.domain.order.OrderBuilder;
import org.portfolio.domain.order.OrderRepository;
import org.portfolio.domain.product.Product;
import org.portfolio.domain.product.ProductBuilder;
import org.portfolio.domain.product.ProductCategory;
import org.portfolio.infrestructure.order.InMemoryOrderRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestCreateOrderUseCase {

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
    public void testCreateOrder(){
        // Crear un pedido esperado
        Order expectedOrder = new OrderBuilder()
                .setId(1)
                .setCustomer(customer)
                .setProducts(products)
                .build();

        CreateOrderUseCase useCase = new CreateOrderUseCase(orderRepository);

        Order actualOrder = useCase.execute(customer, products);

        assertEquals(expectedOrder.getIdOrder(), actualOrder.getIdOrder());
    }

    @Test
    public void testOrderConcatenateId() {

        CreateOrderUseCase useCase = new CreateOrderUseCase(orderRepository);

        Order firstOrder = useCase.execute(customer, products);
        Order secondOrder = useCase.execute(customer, products);
        Order thirdOrder = useCase.execute(customer, products);

        assertEquals(3, thirdOrder.getIdOrder());
    }
}
