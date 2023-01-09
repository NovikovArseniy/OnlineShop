package ru.novikov.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrdersToProductsKey implements Serializable {

    @Column(name = "OrderID")
    int orderId;

    @Column(name = "ProductID")
    int productId;
}
