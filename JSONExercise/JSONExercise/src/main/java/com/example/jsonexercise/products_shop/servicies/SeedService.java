package com.example.jsonexercise.products_shop.servicies;

import java.io.FileNotFoundException;

public interface SeedService {
    void seedUser() throws FileNotFoundException;

    void seedProduct() throws FileNotFoundException;

    void seedCategory() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException {
        seedUser();
        seedCategory();
        seedProduct();
    }
}
