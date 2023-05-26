package ru.novikov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.Role;
import ru.novikov.shop.model.User;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.ProductService;
import ru.novikov.shop.service.UserService;

@Controller
@RequestMapping("/")
public class ShopController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String start(){
        return "redirect:/login";
    }
    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("products", productService.getAll());
        Cart cart = cartService.getCurrent(findCurrentUser());
        model.addAttribute("cartmap", cartService.getCartMap(cart));
        model.addAttribute("totalPrice", cart.getTotalPrice());
        User user = findCurrentUser();
        if (user.getRoles().contains(new Role(2L, "ROLE_ADMIN"))){
            model.addAttribute("admin", true);
        } else {
            model.addAttribute("admin", false);
        }
        return "home";
    }

    @PostMapping("/addproduct")
    public String addProduct(@RequestParam String productName){
        cartService.addProduct(productService.getByName(productName), findCurrentUser());
        return "redirect:/home";
    }

    @GetMapping("/cart")
    public String showCart(Model model){
        Cart cart = cartService.getCurrent(findCurrentUser());
        model.addAttribute("cartmap", cartService.getCartMap(cart));
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "cart";
    }

    @PostMapping("/removeproduct")
    public String removeProduct(@RequestParam String productName){
        cartService.removeProduct(productService.getByName(productName), findCurrentUser());
        return "redirect:/home";
    }
    public User findCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(auth.getName());
    }
}
