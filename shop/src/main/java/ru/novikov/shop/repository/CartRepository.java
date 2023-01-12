package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
