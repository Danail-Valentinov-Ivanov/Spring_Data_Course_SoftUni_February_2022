package com.example.jsonexercise.products_shop.entity.product;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsExportDTO {

    @XmlElement(name = "product")
    List<ProductWithoutBuyerDTO>productWithoutBuyerDTOS;

    public ProductsExportDTO() {
    }

    public ProductsExportDTO(List<ProductWithoutBuyerDTO> productWithoutBuyerDTOS) {
        this.productWithoutBuyerDTOS = productWithoutBuyerDTOS;
    }
}
