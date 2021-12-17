package net.felipesantos.wishlist.common.enums;

import lombok.Getter;

@Getter
public enum MessageEnum {
    PRODUCT_NOT_FOUND("Product id %s not found for customer id %s."),
    WISHLIST_NOT_FOUND("Wishlist for customerId %s not found."),
    PRODUCT_ALREADY_REGISTERED("Product id %s already registered for customer id %s."),
    WISHLIST_FULL("The customer's wish list has reached its limit.");


    private String message;

    MessageEnum(String message) {
        this.message = message;
    }

}
