package ru.novikov.shop.service.implementation;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.OrderRepository;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.OrderService;
import ru.novikov.shop.service.OrdersToProductsService;
import ru.novikov.shop.service.UserService;

import java.util.List;
import java.util.Map;
//TODO: map to list
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CartService cartService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrdersToProductsService ordersToProductsService;

    @Override
    @Transactional
    public Order addOrder(Order order, User user) {
        order.setUser(user);
        order = orderRepository.saveAndFlush(order);
        Cart cart = cartService.getCurrent(user);
        Map<Product, Integer> productMap = cartService.getCartMap(cart);
        for (Map.Entry<Product, Integer> entry : productMap.entrySet()){
            OrdersToProducts ordersToProducts = new OrdersToProducts();
            OrdersToProductsKey ordersToProductsKey = new OrdersToProductsKey();
            ordersToProductsKey.setOrderId(order.getOrderId());
            ordersToProductsKey.setProductId(entry.getKey().getProductId());
            ordersToProducts.setId(ordersToProductsKey);
            ordersToProducts.setOrder(order);
            ordersToProducts.setProduct(entry.getKey());
            ordersToProducts.setAmount(entry.getValue());
            ordersToProductsService.addOrdersToProducts(ordersToProducts);
        }
        return getById(order.getOrderId());
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getByUser(User user){
        return orderRepository.getByUser(user);
    }
}
