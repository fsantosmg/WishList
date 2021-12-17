package net.felipesantos.wishlist.config.builder;

import net.felipesantos.wishlist.domain.model.Product;

public class ProductBuilder {

    private Product product;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        ProductBuilder builder = new ProductBuilder();
        builder.product = new Product();
        return builder;
    }

    public ProductBuilder completeProduct() {
        product.setProductId(DataTest.PRODUCT_ID_TEST);
        product.setName(DataTest.PRODUCT_NAME_TEST);
        product.setPrice(DataTest.PRODUCT_PRICE_TEST);
        return this;
    }


    public Product build() {
        return product;
    }
}