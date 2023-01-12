package ru.novikov.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.novikov.shop.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(Long userId);
    User findCurrentUser();
    User findByUsername(String username);
    List<User> allUsers();
    boolean saveUser(User user);
    boolean deleteUser(Long userId);
    List<User> usergtList(Long idMin);
}
