package com.example.jsonexercise.products_shop.entity.user;

import com.example.jsonexercise.products_shop.entity.product.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    //    TODO: (at least 3 characters)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    private Integer age;

    @OneToMany(mappedBy = "seller", targetEntity = Product.class)
    private List<Product> sellingItems;

    @OneToMany(mappedBy = "buyer", targetEntity = Product.class)
    private List<Product> itemsBought;

    @ManyToMany
    private Set<User>friends;

    public User() {
        sellingItems = new ArrayList<>();
        itemsBought = new ArrayList<>();
        friends = new HashSet<>();
    }

    public User(String firstName, String lastName, Integer age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Product> getSellingItems() {
        return sellingItems;
    }

    public void setSellingItems(List<Product> sellingItems) {
        this.sellingItems = sellingItems;
    }

    public List<Product> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<Product> itemsBought) {
        this.itemsBought = itemsBought;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }
}
