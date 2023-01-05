package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.entities.Product;
import ru.novikov.shop.repository.ProductRepository;
import ru.novikov.shop.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.getProductByProductName(name);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
