package net.felipesantos.wishlist.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter@Setter
public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private String createdAt;

    public Product() {
        this.createdAt = Instant.now().toString();
    }

    public Product(String productId, String name, BigDecimal price) {

        this.productId = productId;
        this.name = name;
        this.price = price;
        this.createdAt = Instant.now().toString();
    }
}

