package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "orders_products")
public class OrdersToProducts {

    @EmbeddedId
    OrdersToProductsKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "OrderID")
    Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "ProductID")
    Product product;

    int amount;

}
