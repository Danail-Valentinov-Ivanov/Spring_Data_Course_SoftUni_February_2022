package com.example.jsonexercise.products_shop.servicies;

import com.example.jsonexercise.products_shop.entity.user.ExportSellersDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();
}
