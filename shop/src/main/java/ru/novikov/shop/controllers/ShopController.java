package ru.novikov.shop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.ProductService;

@Controller
@RequestMapping("/")
@Slf4j
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public String start(){
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("products", productService.getAll());
        Cart cart = cartService.getCurrent();
        model.addAttribute("cartmap", cartService.getCartMap(cart));
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "home";
    }

    @PostMapping("/addproduct")
    public String addProduct(@RequestParam String productName){
        cartService.addProduct(productService.getByName(productName));
        return "redirect:/home";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        Cart cart = cartService.getCurrent();
        model.addAttribute("cartmap", cartService.getCartMap(cart));
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

    @PostMapping("/removeproduct")
    public String removeProduct(@RequestParam String productName){
        cartService.removeProduct(productService.getByName(productName));
        return "redirect:/home";
    }

}
