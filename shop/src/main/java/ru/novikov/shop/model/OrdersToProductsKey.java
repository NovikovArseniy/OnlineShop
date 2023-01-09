package ru.novikov.shop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Data

public class OrdersToProductsKey implements Serializable {

    @Column(name = "orderid")
    Integer orderId;

    @Column(name = "productid")
    Integer productId;
}
