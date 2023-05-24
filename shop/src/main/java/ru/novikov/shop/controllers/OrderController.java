package ru.novikov.shop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.model.User;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.OrderService;
import ru.novikov.shop.service.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("order", new Order());
        return "orderForm";
    }
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors){
        if (errors.hasErrors()){
            return "orderForm";
        }
        orderService.addOrder(order, findCurrentUser());
        cartService.clearCart(findCurrentUser());
        return "redirect:/home";
    }
    public User findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }
}
