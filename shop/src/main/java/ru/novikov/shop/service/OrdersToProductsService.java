package ru.novikov.shop.service;

import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.OrdersToProducts;
import ru.novikov.shop.model.Product;

import java.util.List;

public interface OrdersToProductsService {

    OrdersToProducts addOrdersToProducts(OrdersToProducts ordersToProducts);
    void delete(Order order);
}
