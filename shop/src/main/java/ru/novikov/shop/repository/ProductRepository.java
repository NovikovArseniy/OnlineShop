package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByProductName(String productName);

}
