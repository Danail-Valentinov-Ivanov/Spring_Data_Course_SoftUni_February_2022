package com.example.jsonexercise.products_shop;

import com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO;
import com.example.jsonexercise.products_shop.entity.product.ProductsExportDTO;
import com.example.jsonexercise.products_shop.entity.user.ExportSellersDTO;
import com.example.jsonexercise.products_shop.servicies.ProductsService;
import com.example.jsonexercise.products_shop.servicies.SeedService;
import com.example.jsonexercise.products_shop.servicies.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class ProductsShopRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public ProductsShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;

        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void run(String... args) throws Exception {
//        seedService.seedUser();
//        seedService.seedCategory();
//        seedService.seedProduct();
//        seedService.seedAll();

        productsInPriceRange();
//        findUserWithSoldProducts();
    }

    private void findUserWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(result, System.out);
    }

    private void productsInPriceRange() throws JAXBException, IOException {
        List<ProductWithoutBuyerDTO> productsForSell = productsService
                .getProductsInPriceRangeForSell(500, 1000);
        ProductsExportDTO productsExportDTO = new ProductsExportDTO(productsForSell);

        JAXBContext jaxbContext = JAXBContext.newInstance(ProductsExportDTO.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(productsExportDTO, System.out);

        FileWriter fileWriter = new FileWriter("src/main/resources/exportXMLFiles/export1.xml");
        marshaller.marshal(productsExportDTO, fileWriter);

//        String json = gson.toJson(productsForSell);
//        System.out.println(json);
//
//        FileWriter fileWriter = new FileWriter("src/main/resources/export.json");
//        fileWriter.write(json);
//        fileWriter.close();
    }
}
