package ru.novikov.shop.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.novikov.shop.OnlineShopApplication;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.RoleRepository;
import testcontainers.config.ContainerEnvironment;

@SpringBootTest(classes = OnlineShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CartServiceTest extends ContainerEnvironment {

    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    Product product;
    User user;

    @BeforeEach
    public void init(){
        product = new Product();
        product.setProductName("Rice");
        product.setProductPrice(26);
        productService.addProduct(product);

        product = new Product();
        product.setProductName("Sugar");
        product.setProductPrice(34);
        productService.addProduct(product);

        product = new Product();
        product.setProductName("Tomato");
        product.setProductPrice(46);
        productService.addProduct(product);

        Role role = new Role(1L, "ROLE_USER");
        roleRepository.save(role);

        role = new Role(2L, "ROLE_ADMIN");
        roleRepository.save(role);

        user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setPasswordConfirm("admin");
        user.setPhoneNumber("+79990000000");
        userService.saveAdminUser(user);
        user = userService.findByUsername("admin");
    }
    @AfterEach
    public void clear(){
        cartService.clearCart(user);
    }

    @Test
    public void addProductWhenCartIsEmptyShouldReturnCart(){
        Assertions.assertNotNull(cartService);
        Cart cart = cartService.addProduct(product, user);
        Assertions.assertNotNull(cart);
        CartToProducts cartToProducts = cart.getCartToProductsSet().iterator().next();
        Assertions.assertEquals(cartToProducts.getProduct().getProductName(), product.getProductName());
    }

    @Test
    public void addProductWhenCartIsNotEmptyShouldReturnCorrectAmount(){
        cartService.addProduct(product, user);
        Cart cart = cartService.addProduct(product, user);
        CartToProducts cartToProducts = cart.getCartToProductsSet().iterator().next();
        Assertions.assertEquals(cartToProducts.getAmount(), 2);

    }

    @Test
    public void removeProductFromCartThatHaveAtLeastTwoSameProductsShouldReturnCorrectAmount(){
        cartService.addProduct(product, user);
        cartService.addProduct(product, user);
        Cart cart = cartService.removeProduct(product, user);
        CartToProducts cartToProducts = cart.getCartToProductsSet().iterator().next();
        Assertions.assertEquals(cartToProducts.getAmount(), 1);
    }

    @Test
    public void removeLastProductFromCartShouldReturnEmptyCart(){
        cartService.addProduct(product, user);
        Cart cart = cartService.removeProduct(product, user);
        Assertions.assertTrue(cart.getCartToProductsSet().isEmpty());
    }

    @Test
    public void removeProductWhenCartIsEmptyShouldReturnEmptyCart(){
        Cart cart = cartService.removeProduct(product, user);
        Assertions.assertNotNull(cart);
        Assertions.assertTrue(cart.getCartToProductsSet().isEmpty());
    }
}
