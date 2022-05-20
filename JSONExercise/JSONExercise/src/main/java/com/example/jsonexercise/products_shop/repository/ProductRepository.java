package com.example.jsonexercise.products_shop.repository;

import com.example.jsonexercise.products_shop.entity.product.Product;
import com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select new com.example.jsonexercise.products_shop.entity.product.ProductWithoutBuyerDTO" +
            "(p.name, p.price, p.seller.firstName, p.seller.lastName) from Product p" +
            " where p.price >= :fromPrice and p.price <= :toPrice" +
            " and p.buyer is null" +
            " order by p.price asc")
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc
            (BigDecimal fromPrice, BigDecimal toPrice);
}
