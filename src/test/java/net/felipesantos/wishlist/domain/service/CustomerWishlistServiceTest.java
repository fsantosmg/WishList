package net.felipesantos.wishlist.domain.service;

import net.felipesantos.wishlist.common.enums.MessageEnum;
import net.felipesantos.wishlist.config.builder.DataTest;
import net.felipesantos.wishlist.config.builder.ProductBuilder;
import net.felipesantos.wishlist.config.builder.WishListBuilder;
import net.felipesantos.wishlist.domain.exception.EntityNotFoundException;
import net.felipesantos.wishlist.domain.exception.ProductAlreadyRegisteredException;
import net.felipesantos.wishlist.domain.exception.WishListReachedLimitException;
import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.model.Wishlist;
import net.felipesantos.wishlist.domain.repository.WishlistRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CustomerWishlistServiceTest {

    @Mock
    private WishlistRepository wishlistRepository;

    @InjectMocks
    private CustomerWishlistService customerWishlistService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void testAddPrduct() {
        Optional<Wishlist> empty = Optional.empty();
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(empty);
        customerWishlistService.addProduct(DataTest.CUSTOMER_ID_TEST, ProductBuilder.aProduct().completeProduct().build());
        verify(wishlistRepository, times(1)).save(ArgumentMatchers.any(Wishlist.class));
    }

    @Test
    void testAddPrductWithWishlistFullException() {
        Wishlist wishlistFull = WishListBuilder.aWishlist().wishListFull().build();
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(wishlistFull));
        WishListReachedLimitException thrown = assertThrows(
                WishListReachedLimitException.class,
                () -> customerWishlistService.addProduct(DataTest.CUSTOMER_ID_TEST, ProductBuilder.aProduct().completeProduct().build()));

        assertEquals(MessageEnum.WISHLIST_FULL.getMessage(), thrown.getMessage());
    }

    @Test
    void testAddProductAlreadyRegisteredException() {
        Product product = ProductBuilder.aProduct().completeProduct().build();
        Wishlist wishlistWithRepeatedProduct = new Wishlist(DataTest.CUSTOMER_ID_TEST, Arrays.asList(product));
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(Optional.of(wishlistWithRepeatedProduct));
        ProductAlreadyRegisteredException thrown = assertThrows(
                ProductAlreadyRegisteredException.class,
                () -> customerWishlistService.addProduct(DataTest.CUSTOMER_ID_TEST, product));
        String expectedMessage = String.format(MessageEnum.PRODUCT_ALREADY_REGISTERED.getMessage(), product.getProductId(), DataTest.CUSTOMER_ID_TEST);
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    void testGetWishlistByCustomerId() {
        Optional<Wishlist> wishlist = Optional.of(WishListBuilder
                .aWishlist().withID().withCustomer().withProducts().build());
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(wishlist);
        customerWishlistService.getWishlistByCustomerId(DataTest.CUSTOMER_ID_TEST);
        verify(wishlistRepository, times(1)).findByCustomerId(ArgumentMatchers.any(String.class));
    }

    @Test
    void testGetWishlistByCustomerIdEntityNotFound() {
        Optional<Wishlist> optionalWishlistEmpty = Optional.empty();
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(optionalWishlistEmpty);
        assertThrows(
                EntityNotFoundException.class,
                () -> customerWishlistService.getWishlistByCustomerId(DataTest.CUSTOMER_ID_TEST));
    }

    @Test
    void removeWishlistByCustomerId(){
        Optional<Wishlist> wishlist = Optional.of(WishListBuilder
                .aWishlist().withID().withCustomer().withProducts().build());
        when(wishlistRepository.findByCustomerId(ArgumentMatchers.any(String.class))).thenReturn(wishlist);
        customerWishlistService.removeWishlistByCustomerId(DataTest.CUSTOMER_ID_TEST);
        verify(wishlistRepository, times(1)).delete(ArgumentMatchers.any(Wishlist.class));
    }


}


