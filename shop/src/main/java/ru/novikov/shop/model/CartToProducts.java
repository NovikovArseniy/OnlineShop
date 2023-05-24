package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "carts_products")
@EqualsAndHashCode
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
