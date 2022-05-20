package com.example.jsonexercise.products_shop.servicies;

import com.example.jsonexercise.products_shop.entity.category.CategoryImportDTO;
import com.example.jsonexercise.products_shop.entity.category.Category;
import com.example.jsonexercise.products_shop.entity.product.Product;
import com.example.jsonexercise.products_shop.entity.product.ProductImportDTO;
import com.example.jsonexercise.products_shop.entity.user.User;
import com.example.jsonexercise.products_shop.entity.user.UserImportDTO;
import com.example.jsonexercise.products_shop.repository.CategoryRepository;
import com.example.jsonexercise.products_shop.repository.ProductRepository;
import com.example.jsonexercise.products_shop.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {
    private final static String USER_JSON_PATH = "src/main/resources/productsShop/users.json";
    private final static String CATEGORY_JSON_PATH = "src/main/resources/productsShop/categories.json";
    private final static String PRODUCT_JSON_PATH = "src/main/resources/productsShop/products.json";
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository
            , ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void seedUser() throws FileNotFoundException {
        FileReader fileReader = new FileReader(USER_JSON_PATH);
        UserImportDTO[] userImportDTOS = gson.fromJson(fileReader, UserImportDTO[].class);
        List<User> users = Arrays.stream(userImportDTOS).map(importDTO -> modelMapper
                .map(importDTO, User.class)).collect(Collectors.toList());

        userRepository.saveAll(users);
    }

    @Override
    public void seedProduct() throws FileNotFoundException {
        FileReader fileReader = new FileReader(PRODUCT_JSON_PATH);
        ProductImportDTO[] productImportDTOS = gson.fromJson(fileReader, ProductImportDTO[].class);
        List<Product> products = Arrays.stream(productImportDTOS).map(importDTO -> modelMapper
                        .map(importDTO, Product.class))
                .map(product -> setRandomSeller(product))
                .map(product -> setRandomBuyer(product))
                .map(product -> setRandomCategories(product))
                .collect(Collectors.toList());

        productRepository.saveAll(products);
    }

    @Override
    public void seedCategory() throws FileNotFoundException {
        FileReader fileReader = new FileReader(CATEGORY_JSON_PATH);
        CategoryImportDTO[] categoryImportDTOS = gson.fromJson(fileReader, CategoryImportDTO[].class);
        List<Category> categories = Arrays.stream(categoryImportDTOS).map(importDTO -> modelMapper
                .map(importDTO, Category.class)).collect(Collectors.toList());

        categoryRepository.saveAll(categories);
    }

    private Product setRandomCategories(Product product) {
        long categoriesCount = categoryRepository.count();
        int randomCount = new Random().nextInt((int) categoriesCount) + 1;
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < randomCount; i++) {
            int randomId = new Random().nextInt((int) categoriesCount) + 1;
            Optional<Category> category = categoryRepository.findById(randomId);
            categories.add(category.get());
        }
        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(500)) > 0) {
            return product;
        }
        long usersCount = userRepository.count();
        int randomId = new Random().nextInt((int) usersCount) + 1;
        Optional<User> buyer = userRepository.findById(randomId);
        product.setBuyer(buyer.get());
        return product;
    }

    private Product setRandomSeller(Product product) {
        long usersCount = userRepository.count();
        int randomId = new Random().nextInt((int) usersCount) + 1;
        Optional<User> seller = userRepository.findById(randomId);
        product.setSeller(seller.get());
        return product;
    }
}
