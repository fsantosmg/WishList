package net.felipesantos.wishlist.api.controller;

import net.felipesantos.wishlist.api.dto.ProductDTO;
import net.felipesantos.wishlist.api.dto.WishlistDTO;
import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.model.Wishlist;
import net.felipesantos.wishlist.domain.service.CustomerWishlistService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@OpenAPIDefinition(tags = {@Tag(name = "Customer Wishlist", description = "API to manipulate customer wishlist")},
        info = @io.swagger.v3.oas.annotations.info.Info(title = "Wishlist API", version = "1.0.0"))
@RestController
@RequestMapping("/api/wishlist")
public class CustomerWishlistController {

    private final CustomerWishlistService customerWishlistService;

    private final ModelMapper modelMapper;

    public CustomerWishlistController(CustomerWishlistService customerWishlistService, ModelMapper modelMapper) {
        this.customerWishlistService = customerWishlistService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get wishlist", description = "Get wishlist by customerId")
    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public WishlistDTO getWishlistByCustomerId(@PathVariable String customerId) {

        Wishlist wishlist = customerWishlistService.getWishlistByCustomerId(customerId);

        return new WishlistDTO(wishlist);
    }

    @Operation(summary = "Add product to wishlist", description = "Add product to wishlist")
    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody ProductDTO dto, @PathVariable String customerId) {
        Product product = toDomainProduct(dto);
        customerWishlistService.addProduct(customerId, product);
    }

    @Operation(summary = "Remove product from wishlist", description = "Remove product from wishlist by Id")
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeProduct(@PathVariable("customerId") String customerId, @RequestParam("productId") String productId) {
        customerWishlistService.removeProduct(customerId, productId);
    }

    @Operation(summary = "Remove wishlist", description = "Remove customer's wishlist by clientId")
    @DeleteMapping("/remove/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeWishlistByCustomerId(@PathVariable("customerId") String customerId) {
        customerWishlistService.removeWishlistByCustomerId(customerId);
    }

    private Product toDomainProduct(ProductDTO dto) {
        return modelMapper.map(dto, Product.class);
    }




}
