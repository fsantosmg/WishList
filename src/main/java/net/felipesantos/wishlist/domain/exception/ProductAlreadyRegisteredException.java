package net.felipesantos.wishlist.domain.exception;

public class ProductAlreadyRegisteredException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductAlreadyRegisteredException(String message) {
        super(message);
    }
}
