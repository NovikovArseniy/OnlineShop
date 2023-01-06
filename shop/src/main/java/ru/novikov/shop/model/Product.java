package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid", nullable = false)
    private int productId;

    @Column(name = "productname")
    private String productName;

    @Column(name = "productprice")
    private int productPrice;

    @Column(name = "productdescription")
    private String productDescription;
}
