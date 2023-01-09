package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.OrderRepository;
import ru.novikov.shop.service.OrderService;
import ru.novikov.shop.service.OrdersToProductsService;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrdersToProductsService ordersToProductsService;
    @Autowired
    Cart cart;

    @Override
    public Order addOrder(Order order) {
        order = orderRepository.saveAndFlush(order);
        for (Map.Entry<Product, Integer> entry : cart.getProducts().entrySet()){
            OrdersToProducts ordersToProducts = new OrdersToProducts();
            OrdersToProductsKey ordersToProductsKey = new OrdersToProductsKey();
            ordersToProductsKey.setOrderId(order.getOrderId());
            ordersToProductsKey.setProductId(entry.getKey().getProductId());
            ordersToProducts.setId(ordersToProductsKey);
            ordersToProducts.setOrder(order);
            ordersToProducts.setProduct(entry.getKey());
            ordersToProducts.setAmount(entry.getValue());
            System.out.println("!!!!!!!!!!!!!!!!!");
            System.out.println(ordersToProducts);
            ordersToProductsService.addOrdersToProducts(ordersToProducts);
        }
        return order;
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getByName(String name) {
        return orderRepository.getOrderByCustomerName(name);
    }

    @Override
    public Order getById(int id) {
        return orderRepository.getReferenceById(id);
    }
}
