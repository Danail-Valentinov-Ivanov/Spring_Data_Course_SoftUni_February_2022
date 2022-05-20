package com.example.jsonexercise.products_shop;

import com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO;
import com.example.jsonexercise.products_shop.servicies.ProductsService;
import com.example.jsonexercise.products_shop.servicies.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.FileWriter;
import java.util.List;

@Component
public class ProductsShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductsService productsService;
    private final Gson gson;

    @Autowired
    public ProductsShopRunner(SeedService seedService, ProductsService productsService) {
        this.seedService = seedService;
        this.productsService = productsService;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedUser();
//        seedService.seedCategory();
//        seedService.seedProduct();
//        seedService.seedAll();

        List<ProductWithoutBuyerDTO> productsForSell = productsService
                .getProductsInPriceRangeForSell(500, 1000);
        String json = gson.toJson(productsForSell);
        System.out.println(json);

        FileWriter fileWriter = new FileWriter("src/main/resources/export.json");
        fileWriter.write(json);
        fileWriter.close();
    }
}
