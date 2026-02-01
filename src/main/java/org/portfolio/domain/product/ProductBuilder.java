package org.portfolio.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductBuilder {

    private int idProduct;
    private String title;
    private String description;
    private ProductCategory category;
    private int stock;
    private BigDecimal price;

    public ProductBuilder setId(int id) {
        if(id == 0) {
            throw new IllegalArgumentException("id cannot be 0");
        }
        else this.idProduct = id;
        return this;
    }

    public ProductBuilder setTitle(String title) {
        if(title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        else this.title = title;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        if(description == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        else this.description = description;
        return this;
    }

    public ProductBuilder setCategory(ProductCategory category) {
        if(category == null) {
            throw new IllegalArgumentException("category cannot be null");
        }
        else this.category = category;
        return this;
    }

    public ProductBuilder setStock(int stock) {
        if(stock < 0) {
            throw new IllegalArgumentException("stock cannot be negative");
        }
        else this.stock = stock;
        return this;
    }

    public ProductBuilder setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        else this.price = price;
        return this;
    }

    public Product build() {
        if (idProduct == 0) {
            throw new IllegalArgumentException("id cannot be 0");
        }
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(description, "description cannot be null");
        Objects.requireNonNull(category, "category cannot be null");
        if(stock < 0) {
            throw new IllegalArgumentException("stock cannot be negative");
        }
        if(price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }

        return new Product(
            idProduct,
            title,
            description,
            category,
            stock,
            price
        );
    }
}
