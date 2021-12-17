package net.felipesantos.wishlist.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class Wishlist {

    @Id
    private String id;

    @Indexed(unique = true)
    private String customerId;

    private List<Product> products;

    public Wishlist() {
        this.products = new ArrayList<>();
    }

    public Wishlist(String customerId) {
        this.customerId = customerId;
        this.products = new ArrayList<>();
    }

    public Wishlist(String customerId, List<Product> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public void addProduct(Product product) {
        if (products == null)
            products = new ArrayList<>();
        this.products.add(product);
    }


    public boolean productAlreadyExists(String productId) {
        return this.products.stream().anyMatch(product -> product.getProductId().equals(productId));
    }


}

