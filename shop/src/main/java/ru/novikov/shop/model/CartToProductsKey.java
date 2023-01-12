package ru.novikov.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CartToProductsKey implements Serializable {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "productid")
    Long productId;
}
