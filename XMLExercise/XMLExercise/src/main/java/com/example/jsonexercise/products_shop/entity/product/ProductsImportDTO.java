package com.example.jsonexercise.products_shop.entity.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportDTO {

    @XmlElement(name = "product")
    private List<ProductImportDTO>productImportDTOS;

    public ProductsImportDTO() {
    }

    public List<ProductImportDTO> getProductImportDTOS() {
        return productImportDTOS;
    }
}
