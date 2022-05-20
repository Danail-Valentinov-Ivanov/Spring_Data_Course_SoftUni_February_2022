package com.example.jsonexercise.products_shop.servicies;

import com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductsService {
    List<ProductWithoutBuyerDTO>getProductsInPriceRangeForSell(float from, float to);
}
