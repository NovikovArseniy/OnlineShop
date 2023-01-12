package ru.novikov.shop.service;

import ru.novikov.shop.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    void delete(Long id);
    Product getByName(String name);
    Product editProduct(Product product);
    List<Product> getAll();
}