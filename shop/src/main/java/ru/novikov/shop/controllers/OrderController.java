package ru.novikov.shop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.Order;
import ru.novikov.shop.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    Cart cart;

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
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(order);
        orderService.addOrder(order);
        cart.clear();
        return "redirect:/";
    }
}
