package ru.novikov.shop.model;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

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
}
