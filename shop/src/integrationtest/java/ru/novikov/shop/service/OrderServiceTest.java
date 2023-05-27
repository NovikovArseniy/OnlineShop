package ru.novikov.shop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.novikov.shop.OnlineShopApplication;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.RoleRepository;
import testcontainers.config.ContainerEnvironment;

import java.util.Set;

@SpringBootTest(classes = OnlineShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class OrderServiceTest extends ContainerEnvironment {

    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;
    User user;
    Cart cart;

    @BeforeEach
    public void init(){
        Product product = new Product();
        product.setProductName("Rice");
        product.setProductPrice(26);
        productService.addProduct(product);

        product = new Product();
        product.setProductName("Sugar");
        product.setProductPrice(34);
        productService.addProduct(product);

        product = new Product();
        product.setProductName("Tomato");
        product.setProductPrice(46);
        productService.addProduct(product);

        Role role = new Role(1L, "ROLE_USER");
        roleRepository.save(role);

        role = new Role(2L, "ROLE_ADMIN");
        roleRepository.save(role);

        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setPhoneNumber("+79990000000");
        userService.saveUser(user);
        user = userService.findByUsername("username");
        cart = cartService.getCurrent(user);
    }

    @Test
    public void addOrderTest(){
        cartService.addProduct(productService.getByName("Sugar"), user);
        cartService.addProduct(productService.getByName("Sugar"), user);
        cartService.addProduct(productService.getByName("Rice"), user);
        Order order = new Order();
        order.setAddress("some street 18-23");
        order.setCity("London");
        order.setCcNumber("4276223678452290");
        order.setCcCVV("432");
        order.setCcExpiration("05/25");
        orderService.addOrder(order, user);
        order = orderService.getByUser(user).iterator().next();
        Assertions.assertEquals(order.getTotalPrice(), 94);
    }
}
