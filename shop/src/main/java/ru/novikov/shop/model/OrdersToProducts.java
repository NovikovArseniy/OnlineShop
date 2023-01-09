package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
