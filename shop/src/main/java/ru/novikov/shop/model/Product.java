package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid", nullable = false)
    private int productId;

    @Column(name = "productname")
    @NonNull
    private String productName;

    @Column(name = "productprice")
    @NonNull
    private int productPrice;

    @Column(name = "productdescription")
    private String productDescription;

    public Product(String productName, int productPrice, String productDescription){
        this(productName, productPrice);
        this.productDescription = productDescription;
    }
}
