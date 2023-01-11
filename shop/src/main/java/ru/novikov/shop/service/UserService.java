package ru.novikov.shop.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.novikov.shop.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User findUserById(Long userId);
    public List<User> allUsers();
    public boolean saveUser(User user);
    public boolean deleteUser(Long userId);
    public List<User> usergtList(Long idMin);
}
