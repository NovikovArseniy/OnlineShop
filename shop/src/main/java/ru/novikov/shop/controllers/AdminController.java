package ru.novikov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Product;
import ru.novikov.shop.service.OrderService;
import ru.novikov.shop.service.ProductService;
import ru.novikov.shop.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

    @PostMapping
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model){
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @GetMapping("/productlist")
    public String getAllProducts(Model model){
        model.addAttribute("productList", productService.getAll());
        return "productList";
    }

    @PostMapping(value = "/addproduct")
    public String addProduct(@RequestParam String productName,
                              @RequestParam int productPrice,
                              @RequestParam String productDescription){
        productService.addProduct(new Product(productName, productPrice, productDescription));
        return "redirect:/productList";
    }

    //TODO:
    @DeleteMapping(value = "/removeproduct")
    public String removeProduct(@RequestParam Long id){
        productService.delete(id);
        return "redirect:/productList";
    }

    @GetMapping("/userlist")
    public String userList(Model model){
        model.addAttribute("users", userService.allUsers());
        return "userList";
    }

    @GetMapping("/orderListAdmin")
    public String orderList(Model model){
        model.addAttribute("orderList", orderService.getAll());
        return "orderList";
    }
}
