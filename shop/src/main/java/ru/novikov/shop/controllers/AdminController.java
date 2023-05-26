package ru.novikov.shop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Order;
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

    @GetMapping
    public String adminHomePage(){
        return "admin";
    }
    @GetMapping("/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }

    @GetMapping("/productlist")
    public String getAllProducts(Model model){
        model.addAttribute("products", productService.getAll());
        return "productList";
    }

    @GetMapping("/addproduct")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/addproduct")
    public String addProductProcess(@Valid Product product, Errors errors){
        if (errors.hasErrors()){
            return "addProduct";
        }
        productService.addProduct(product);
        return "redirect:/admin/productlist";
    }

    //TODO:
    @DeleteMapping("/removeproduct")
    public String removeProduct(@RequestParam Long id){
        productService.delete(id);
        return "redirect:/admin/productlist";
    }

    @GetMapping("/userlist")
    public String userList(Model model){
        model.addAttribute("users", userService.allUsers());
        return "userList";
    }

    @GetMapping("/orderListAdmin")
    public String orderList(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "orderList";
    }

    @GetMapping("/orderListAdmin/{id}")
    public String orderListByUserId(@PathVariable("id") Long userId, Model model){
        model.addAttribute("orders", orderService.getByUser(userService.findUserById(userId)));
        return "orderList";
    }

    @GetMapping("/orderInfo/{orderId}")
    public String orderInfo(@PathVariable("orderId") Long orderId, Model model){
        Order order = orderService.getById(orderId);
        System.out.println(order.getOrdersToProductsSet().isEmpty());
        model.addAttribute("orderToProductsList", order.getOrdersToProductsSet());
        model.addAttribute("totalPrice", order.getTotalPrice());
        return "orderInfo";
    }
}
