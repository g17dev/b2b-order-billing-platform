package org.portfolio.domain.product;

import java.math.BigDecimal;

public class Product {
    private int idProduct;
    private String title;
    private String description;
    private ProductCategory category;
    private int Stock;
    private BigDecimal price;

    public Product(
            int idProduct,
            String title,
            String description,
            ProductCategory category,
            int stock,
            BigDecimal price) {
        this.idProduct = idProduct;
        this.title = title;
        this.description = description;
        this.category = category;
        this.Stock = stock;
        this.price = price;
    }

    // getters
    public int getIdProduct() { return  idProduct; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public ProductCategory getCategory() { return category; }
    public int getStock() { return Stock; }
    public BigDecimal getPrice() {
        return price;
    }
}
