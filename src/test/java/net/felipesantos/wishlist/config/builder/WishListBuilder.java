package net.felipesantos.wishlist.config.builder;


import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.model.Wishlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class WishListBuilder {



    private Wishlist wishList;

    private WishListBuilder() {
    }
    public static WishListBuilder aWishlist() {
        WishListBuilder builder = new WishListBuilder();
        builder.wishList = new Wishlist();
        return builder;
    }

    public WishListBuilder withProducts(){

        wishList.addProduct(ProductBuilder.aProduct().completeProduct().build());
        return this;
    }

    public WishListBuilder withID(){
        wishList.setId(DataTest.WISHLIST_ID_TEST);
        return this;
    }

    public WishListBuilder withCustomer(){
        wishList.setCustomerId(DataTest.CUSTOMER_ID_TEST);
        return this;
    }

    public WishListBuilder wishListFull(){
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            products.add(new Product("idCode" + i, "Product " + i, new BigDecimal(i+1*10)));
        }
        wishList.setProducts(products);
        return this;
    }
    public Wishlist build() {
        return wishList;
    }




}
