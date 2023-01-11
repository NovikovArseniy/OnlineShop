package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
