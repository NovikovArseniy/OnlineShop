package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid", nullable = false)
    private Long productId;

    @Column(name = "productname")
    @NonNull
    private String productName;

    @Column(name = "productprice")
    @NonNull
    private int productPrice;

    @Column(name = "productdescription")
    private String productDescription;

    @OneToMany(mappedBy = "product")
    Set<OrdersToProducts> ordersToProductsSet;

    @OneToMany(mappedBy = "product")
    Set<CartToProducts> cartToProductsSet;

    public Product(String productName, int productPrice, String productDescription){
        this(productName, productPrice);
        this.productDescription = productDescription;
    }
}
