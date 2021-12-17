package net.felipesantos.wishlist.api.dto;

import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.model.Wishlist;
import lombok.Getter;

import java.util.List;

@Getter
public class WishlistDTO {
    private String id;
    private List<Product> products;

    public WishlistDTO(Wishlist wishlist) {
        this.id = wishlist.getId();
        this.products = wishlist.getProducts();
    }
}
