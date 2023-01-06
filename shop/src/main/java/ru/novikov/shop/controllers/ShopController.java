package ru.novikov.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.Product;
import ru.novikov.shop.service.ProductService;

@Controller
@RequestMapping("/")
@Slf4j
@Scope("session")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private Cart cart;

    @GetMapping
    public String homePage(Model model){
        model.addAttribute("products", productService.getAll());
        return "home";
    }

    @PostMapping("/addproduct")
    public String addProduct(@RequestParam String productName){
        cart.addProduct(productService.getByName(productName));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(cart.getTotalPrice());
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String test(Model model){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(cart.getTotalPrice());
        model.addAttribute("cart", cart.getProducts());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

}
