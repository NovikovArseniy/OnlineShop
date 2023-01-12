package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;


//@Component
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private int totalPrice;

    @OneToMany(mappedBy = "cart")
    Set<CartToProducts> cartToProductsSet;
    /*
    @NonNull
    private HashMap<Product, Integer> products;
    private int totalPrice;

    public Cart(){
        this.products = new HashMap<>();
        this.totalPrice = 0;
    }

    public void addProduct(Product product){
        if (products.containsKey(product)){
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
        totalPrice += product.getProductPrice();
    }

    public boolean removeProduct(Product product){
        if (products.containsKey(product)){
            if (products.get(product) == 1){
                products.remove(product);
            } else {
                products.replace(product, products.get(product) - 1);
            }
            totalPrice -= product.getProductPrice();
            return true;
        } else {
            return false;
        }
    }

    public void clear(){
        this.products = new HashMap<>();
        this.totalPrice = 0;
    }

     */
}
