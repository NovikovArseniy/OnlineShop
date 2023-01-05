package ru.novikov.shop.service;

import ru.novikov.shop.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    void delete(int id);
    Product getByName(String name);
    Product editProduct(Product product);
    List<Product> getAll();
}
