package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.repository.OrderRepository;
import ru.novikov.shop.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order addOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order getByName(String name) {
        return orderRepository.getOrderByName(name);
    }

    @Override
    public Order getById(int id) {
        return orderRepository.getReferenceById(id);
    }
}
