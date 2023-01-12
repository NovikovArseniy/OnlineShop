package ru.novikov.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.CartRepository;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.CartToProductsService;
import ru.novikov.shop.service.UserService;

import java.util.List;
import java.util.Map;
//TODO: нормальная сумма при запуске
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartToProductsService cartToProductsService;

    @Override
    public Cart addCart(Cart cart) {
        cart = cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public Cart getById(int id) {
        return null;
    }

    @Override
    public Cart addProduct(Product product) {
        User user = userService.findCurrentUser();
        Cart cart = cartRepository.getReferenceById(user.getId());
        CartToProducts cartToProducts = cartToProductsService.getByCartAndProduct(cart, product);
        if (cartToProducts != null) {
            cartToProducts.setAmount(cartToProducts.getAmount() + 1);
        } else {
            cartToProducts = new CartToProducts();
            cartToProducts.setCart(cart);
            cartToProducts.setProduct(product);
            CartToProductsKey cartToProductsKey = new CartToProductsKey();
            cartToProductsKey.setUserId(cart.getUserId());
            cartToProductsKey.setProductId(product.getProductId());
            cartToProducts.setId(cartToProductsKey);
            cartToProducts.setAmount(1);
        }
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartToProductsService.addCartToProducts(cartToProducts);
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Cart removeProduct(Product product) {
        User user = userService.findCurrentUser();
        Cart cart = cartRepository.getReferenceById(user.getId());
        CartToProducts cartToProducts = cartToProductsService.getByCartAndProduct(cart, product);
        if (cartToProducts == null){
            return cart;
        } else if (cartToProducts.getAmount() == 1) {
            cartToProductsService.deleteByCartAndProduct(cart, product);
        } else {
                cartToProducts.setAmount(cartToProducts.getAmount() - 1);
                cartToProductsService.addCartToProducts(cartToProducts);
        }
        cart.setTotalPrice(calculateTotalPrice(cart));
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Map<Product, Integer> getCartMap(Cart cart) {
        return cartToProductsService.getCartMap(cart);
    }

    @Override
    public Cart getCurrent() {
        return cartRepository.getReferenceById(userService.findCurrentUser().getId());
    }

    @Override
    public void clearCart(Cart cart) {
        cart.setTotalPrice(0);
        cartRepository.save(cart);
        cartToProductsService.deleteByCart(cart);
    }

    public int calculateTotalPrice(Cart cart){
        int totalPrice = 0;
        List<CartToProducts> cartToProductsList = cartToProductsService.getByCart(cart);
        for (CartToProducts entry : cartToProductsList){
            totalPrice += entry.getProduct().getProductPrice() * entry.getAmount();
        }
        return totalPrice;
    }
}
