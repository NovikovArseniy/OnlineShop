package ru.novikov.shop.service.implementation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.novikov.shop.model.Cart;
import ru.novikov.shop.model.CartToProducts;
import ru.novikov.shop.model.Product;
import ru.novikov.shop.repository.CartToProductsRepository;
import ru.novikov.shop.service.CartToProductsService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CartToProductsServiceImpl implements CartToProductsService {

    @Autowired
    CartToProductsRepository cartToProductsRepository;

    @Override
    public CartToProducts addCartToProducts(CartToProducts cartToProducts) {
        return cartToProductsRepository.saveAndFlush(cartToProducts);
    }

    @Override
    @Transactional
    public Integer deleteByCart(Cart cart) {
        return cartToProductsRepository.removeByCart(cart);
    }


    @Override
    @Transactional
    public CartToProducts getByCartAndProduct(Cart cart, Product product) {
        return cartToProductsRepository.getByCartAndProduct(cart, product);
    }

    @Override
    public Map<Product, Integer> getCartMap(Cart cart) {
        List<CartToProducts> cartToProductsList = cartToProductsRepository.getByCart(cart);
        Map<Product, Integer> cartMap = cartToProductsList.stream()
                .collect(Collectors.toMap(CartToProducts::getProduct, CartToProducts::getAmount));
        return cartMap;
    }

    @Override
    public List<CartToProducts> getByCart(Cart cart) {
        return cartToProductsRepository.getByCart(cart);
    }

    @Override
    @Transactional
    public Integer deleteByCartAndProduct(Cart cart, Product product) {
        return cartToProductsRepository.removeByCartAndProduct(cart, product);
    }


}
