package net.felipesantos.wishlist.domain.service;

import net.felipesantos.wishlist.common.enums.MessageEnum;
import net.felipesantos.wishlist.domain.exception.EntityNotFoundException;
import net.felipesantos.wishlist.domain.exception.WishListReachedLimitException;
import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.exception.ProductAlreadyRegisteredException;
import net.felipesantos.wishlist.domain.model.Wishlist;
import net.felipesantos.wishlist.domain.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerWishlistService {

    private WishlistRepository wishlistRepository;

    @Value("${customer.wishlist.limit}")
    private int wishlistLimit;

    public CustomerWishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void addProduct(String customerId, Product product) {

        Wishlist wishlist = new Wishlist(customerId);

        Optional<Wishlist> optionalWishlist = wishlistRepository.findByCustomerId(customerId);

        if (optionalWishlist.isPresent()) {

            if (optionalWishlist.get().productAlreadyExists(product.getProductId()))
                throw new ProductAlreadyRegisteredException(
                        String.format(MessageEnum.PRODUCT_ALREADY_REGISTERED.getMessage(), product.getProductId(), customerId));
            if (optionalWishlist.get().getProducts().size() >= wishlistLimit)
                throw new WishListReachedLimitException(MessageEnum.WISHLIST_FULL.getMessage());

            wishlist.setId(optionalWishlist.get().getId());
            wishlist.getProducts().addAll(optionalWishlist.get().getProducts());
        }
        wishlist.addProduct(product);

        wishlistRepository.save(wishlist);
    }

    public Wishlist getWishlistByCustomerId(String customerId) {
        return wishlistRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MessageEnum.WISHLIST_NOT_FOUND.getMessage(), customerId)));
    }

    public void removeProduct(String customerId, String productId) {

        Optional<Wishlist> optionalWishlist = wishlistRepository.findByCustomerId(customerId);
        if (optionalWishlist.isEmpty())
            throw new EntityNotFoundException(String.format(MessageEnum.WISHLIST_NOT_FOUND.getMessage(), customerId));

        boolean removed = optionalWishlist.get().getProducts().removeIf(product -> product.getProductId().equals(productId));

        if (!removed)
            throw new EntityNotFoundException(String.format(MessageEnum.PRODUCT_NOT_FOUND.getMessage(), productId, customerId));

        wishlistRepository.save(optionalWishlist.get());

    }

    public void removeWishlistByCustomerId(String customerId) {
        Optional<Wishlist> optionalWishlist = wishlistRepository.findByCustomerId(customerId);

        if (optionalWishlist.isPresent()) {
            wishlistRepository.delete(optionalWishlist.get());
        }
    }
}

