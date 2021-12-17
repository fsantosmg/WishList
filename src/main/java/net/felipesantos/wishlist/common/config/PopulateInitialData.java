package net.felipesantos.wishlist.common.config;

import net.felipesantos.wishlist.domain.model.Product;
import net.felipesantos.wishlist.domain.model.Wishlist;
import net.felipesantos.wishlist.domain.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class PopulateInitialData implements CommandLineRunner {

    private final WishlistRepository wishlistRepository;
    @Value("${customer.wishlist.limit}")
    private int wishlistLimit;

    public PopulateInitialData(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public void run(String... args) {

        wishlistRepository.deleteAll();

        Wishlist wishlistA = new Wishlist(
                "1k7e1lcikt",
                Arrays.asList(
                        new Product(
                                "kfgj914cf5",
                                "Microondas LG 30 Litros Branco MS3091  110V",
                                new BigDecimal("529")),
                        new Product(
                                "155596200",
                                "iPhone 12 Pro Max Apple 128GB",
                                new BigDecimal("9299"))));

        Wishlist wishlistB = new Wishlist(
                "d86edu543q",
                Arrays.asList(
                        new Product(
                                "155620900",
                                "Smartphone Samsung Galaxy S21 Ultra 256GB",
                                new BigDecimal("6699")),
                        new Product(
                                "ec35g95k29",
                                "Notebook Dell XPS 13 9310-MS30S UltraHD Touch",
                                new BigDecimal("13099"))));

        Wishlist wishlistC = new Wishlist(
                "8lgkpxw9ju",
                Arrays.asList(
                        new Product(
                                "jfc946j2d0",
                                "Rack Bancada Paladio TV 55 Pol. Sala Com Pes de Madeira - MOVEIS BECHARA",
                                new BigDecimal("318.49"))));

        Wishlist wishlistD = new Wishlist();
        wishlistD.setCustomerId("9lgkpxw9ju");

        Wishlist wishlistCheia = new Wishlist();
        wishlistCheia.setCustomerId("0820fqsviv");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < wishlistLimit; i++) {
            products.add(new Product("idCode" + i, "Product " + i, new BigDecimal(i+1*10)));
        }
        wishlistCheia.setProducts(products);

        wishlistRepository.saveAll(Arrays.asList(wishlistA, wishlistB, wishlistC, wishlistD, wishlistCheia));
    }


}
