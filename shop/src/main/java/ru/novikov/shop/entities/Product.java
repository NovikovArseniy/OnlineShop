package ru.novikov.shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
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
    private String ProductDescription;
}
