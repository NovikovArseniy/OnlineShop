package ru.novikov.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.novikov.shop.OnlineShopApplication;
import ru.novikov.shop.model.Role;
import ru.novikov.shop.model.User;
import ru.novikov.shop.repository.RoleRepository;
import ru.novikov.shop.repository.UserRepository;
import testcontainers.config.ContainerEnvironment;

@SpringBootTest(classes = OnlineShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UserServiceTest extends ContainerEnvironment {

    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    //@Autowired
    //BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    User user;

    @BeforeEach
    public void init(){
        Role role = new Role(1L, "ROLE_USER");
        roleRepository.save(role);

        role = new Role(2L, "ROLE_ADMIN");
        roleRepository.save(role);

        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setPasswordConfirm("password");
        user.setPhoneNumber("+79990000000");
    }

    @AfterEach
    public void clear(){
        userRepository.deleteAll();
    }

    @Test
    public void saveUserWhichIsNotExists(){
        userService.saveUser(user);
        User userFromDb = userService.findByUsername("username");
        Assertions.assertEquals(user.getUsername(), userFromDb.getUsername());
        //Assertions.assertEquals(userFromDb.getPassword(), bCryptPasswordEncoder.encode(user.getPassword()));
        Assertions.assertEquals(user.getPhoneNumber(), userFromDb.getPhoneNumber());
        Assertions.assertEquals(userFromDb.getRoles().iterator().next().getName(), "ROLE_USER");
        Assertions.assertEquals(userFromDb.getCart().getTotalPrice(), 0);
    }

    @Test
    public void saveUserWhichIsAlreadyExists(){
        userService.saveUser(user);
        user.setPassword("anotherPassword");
        user.setPhoneNumber("+79250123456");
        Assertions.assertFalse(userService.saveUser(user));
    }

}
