package ru.novikov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.novikov.shop.entities.Product;
import ru.novikov.shop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopRestApiController {

    @Autowired
    ProductService productService;

    @GetMapping("/productlist")
    public List<Product> getAllProducts(){
        return productService.getAll();
    }

    @GetMapping("/getbyname")
    public Product getProductByName(@RequestParam String name){
        return productService.getByName(name);
    }
}
