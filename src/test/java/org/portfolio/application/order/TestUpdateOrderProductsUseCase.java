package org.portfolio.application.order;

import org.junit.jupiter.api.Test;
import org.portfolio.domain.customer.Customer;
import org.portfolio.domain.customer.CustomerBuilder;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUpdateOrderProductsUseCase {

    private static List<Product> products1 = Arrays.asList(

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

    private static List<Product> products2 = Arrays.asList(

            new ProductBuilder()
                    .setId(1)
                    .setTitle("iPhone 17 Pro")
                    .setDescription("256GB de almacenamiento")
                    .setCategory(ProductCategory.ELECTRONICS)
                    .setStock(10)
                    .setPrice(new BigDecimal("20000"))
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
    public void testUpdateProductsOfOrder() {
        // Crear un pedido esperado
        Order expectedOrder = new OrderBuilder()
                    .setId(1)
                    .setCustomer(customer)
                    .setProducts(products2)
                    .build();

        CreateOrderUseCase useCaseCreateOrder = new CreateOrderUseCase(orderRepository);
        useCaseCreateOrder.execute(customer, products1);

        UpdateOrderProductsUseCase useCaseUpdateOrder = new UpdateOrderProductsUseCase(orderRepository);
        useCaseUpdateOrder.execute(1, products2);

        assertEquals(products2.size(), expectedOrder.getProducts().size());
    }
}
