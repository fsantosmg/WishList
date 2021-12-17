package net.felipesantos.wishlist.domain.exception;

public class WishListReachedLimitException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WishListReachedLimitException(String message) {
        super(message);
    }
}
