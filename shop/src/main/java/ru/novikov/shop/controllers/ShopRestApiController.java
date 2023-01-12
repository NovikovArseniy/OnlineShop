package ru.novikov.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.novikov.shop.model.Product;
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

    @PostMapping(value = "/addproduct")
    public Product addProduct(@RequestParam String productName,
                              @RequestParam int productPrice,
                              @RequestParam String productDescription){
        return productService.addProduct(new Product(productName, productPrice, productDescription));
    }
    @DeleteMapping(value = "/removeproduct")
    public void removeProduct(@RequestParam Long id){
        productService.delete(id);
    }
}
