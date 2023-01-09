package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.OrdersToProducts;
import ru.novikov.shop.repository.OrdersToProductsRepository;
import ru.novikov.shop.service.OrdersToProductsService;

@Service
public class OrdersToProductsServiceImpl implements OrdersToProductsService {

    @Autowired
    OrdersToProductsRepository ordersToProductsRepository;

    @Override
    public OrdersToProducts addOrdersToProducts(OrdersToProducts ordersToProducts) {
        return ordersToProductsRepository.saveAndFlush(ordersToProducts);
    }

    @Override
    public void delete(Order order) {
        ordersToProductsRepository.deleteByOrder(order);
    }
}
