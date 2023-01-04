package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
