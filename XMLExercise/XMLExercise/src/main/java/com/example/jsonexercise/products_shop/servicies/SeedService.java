package com.example.jsonexercise.products_shop.servicies;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SeedService {
    void seedUser() throws IOException, JAXBException;

    void seedProduct() throws IOException, JAXBException;

    void seedCategory() throws IOException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedUser();
        seedCategory();
        seedProduct();
    }
}
