package ru.novikov.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.novikov.shop.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
