package ru.novikov.shop.service.implementation;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.*;
import ru.novikov.shop.repository.CartRepository;
import ru.novikov.shop.service.CartService;
import ru.novikov.shop.service.CartToProductsService;
import ru.novikov.shop.service.UserService;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartToProductsService cartToProductsService;

    @Override
    public Cart addCart(Cart cart) {
        cart = cartRepository.saveAndFlush(cart);
        return cart;
    }

    @Override
    public Cart getById(Long id) {
        return cartRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public Cart addProduct(Product product, User user) {
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
            cart.getCartToProductsSet().add(cartToProducts);
        }
        cartToProductsService.addCartToProducts(cartToProducts);
        cart.calculateAndSetTotalPrice();
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    @Transactional
    public Cart removeProduct(Product product, User user) {
        Cart cart = Hibernate.unproxy(cartRepository.getReferenceById(user.getId()), Cart.class);
        CartToProducts cartToProducts = cartToProductsService.getByCartAndProduct(cart, product);
        if (cartToProducts == null){
            return cart;
        } else if (cartToProducts.getAmount() == 1) {
            cartToProductsService.deleteByCartAndProduct(cart, product);
            cart.getCartToProductsSet().remove(cartToProducts);
        } else {
                cartToProducts.setAmount(cartToProducts.getAmount() - 1);
                cartToProductsService.addCartToProducts(cartToProducts);
        }
        cart = cartRepository.getReferenceById(user.getId());
        cart.calculateAndSetTotalPrice();
        return cartRepository.saveAndFlush(cart);
    }

    @Override
    public Map<Product, Integer> getCartMap(Cart cart) {
        return cartToProductsService.getCartMap(cart);
    }

    @Override
    public Cart getCurrent(User user) {
        return cartRepository.getReferenceById(user.getId());
    }

    @Override
    @Transactional
    public void clearCart(User user) {
        Cart cart = getCurrent(user);
        cart.setTotalPrice(0);
        cart.getCartToProductsSet().clear();
        cartRepository.save(cart);
        cartToProductsService.deleteByCart(cart);
    }

}