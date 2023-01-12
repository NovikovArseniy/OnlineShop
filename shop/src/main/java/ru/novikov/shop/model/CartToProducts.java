package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carts_products")
public class CartToProducts {

    @EmbeddedId
    CartToProductsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    Cart cart;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    Product product;

    int amount;

}
