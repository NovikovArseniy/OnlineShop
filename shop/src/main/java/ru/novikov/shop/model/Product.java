package ru.novikov.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

//TODO: названия столбцов
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
    @NotBlank(message = "input name")
    private String productName;

    @Column(name = "productprice")
    @NonNull
    @Min(value = 1, message = "must be positive")
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
