package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.OrderRepository;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.OrderService;
import ru.novikov.shop.service.OrdersToProductsService;
import ru.novikov.shop.service.UserService;
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
    @Autowired
    UserService userService;

    @Override
    public Order addOrder(Order order) {
        order.setUser(userService.findCurrentUser());
        order = orderRepository.saveAndFlush(order);
        Cart cart = cartService.getCurrent();
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
        return order;
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    //@Override
    //public Order getByName(String name) {
      //  return orderRepository.getOrderByCustomerName(name);
    //}

    @Override
    public Order getById(Long id) {
        return orderRepository.getReferenceById(id);
    }
}
