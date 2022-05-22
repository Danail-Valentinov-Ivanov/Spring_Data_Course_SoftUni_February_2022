package com.example.jsonexercise.products_shop.entity.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersImportDTO {

    @XmlElement(name = "user")
    List<UserImportDTO>userImportDTOS;

    public UsersImportDTO() {
    }

    public List<UserImportDTO> getUserImportDTOS() {
        return userImportDTOS;
    }
}
