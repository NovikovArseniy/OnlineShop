package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.OrdersToProducts;


public interface OrdersToProductsService {

    OrdersToProducts addOrdersToProducts(OrdersToProducts ordersToProducts);
    void delete(Order order);
}