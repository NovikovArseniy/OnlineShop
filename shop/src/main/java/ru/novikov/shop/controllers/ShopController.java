package ru.novikov.shop.controllers;

import jakarta.servlet.http.HttpSession;
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
@SessionAttributes("cart")
public class ShopController {

    @Autowired
    private ProductService productService;

    /*@ModelAttribute("cart")
    public Cart createCart(){
        return new Cart();
    }*/

    @GetMapping
    public String start(HttpSession session){
        session.setAttribute("cart", new Cart());
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("products", productService.getAll());
        return "home";
    }

    @PostMapping("/addproduct")
    public String addProduct(@RequestParam String productName, @SessionAttribute("cart") Cart cart,
                             HttpSession session){
        System.out.println(cart.getProducts());
        cart.addProduct(productService.getByName(productName));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(cart.getTotalPrice());
        return "redirect:/home";
    }

    @GetMapping("/cart")
    public String showCart(Model model, @SessionAttribute("cart") Cart cart){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(cart.getTotalPrice());
        model.addAttribute("cartmap", cart.getProducts());
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

}
