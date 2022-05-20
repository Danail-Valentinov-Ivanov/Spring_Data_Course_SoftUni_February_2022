package com.example.jsonexercise.products_shop.servicies;

import com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO;
import com.example.jsonexercise.products_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductsServiceImpl implements ProductsService {
    private final ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(float from, float to) {
        BigDecimal fromPrice = BigDecimal.valueOf(from);
        BigDecimal toPrice = BigDecimal.valueOf(to);

        return productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(fromPrice, toPrice);
    }
}
