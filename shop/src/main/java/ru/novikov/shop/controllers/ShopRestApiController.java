package ru.novikov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.novikov.shop.entities.Product;
import ru.novikov.shop.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopRestApiController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/productlist")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
