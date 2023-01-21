package ru.novikov.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

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

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    Set<CartToProducts> cartToProductsSet;

    public int calculateAndSetTotalPrice(){
        int result = 0;
        for (CartToProducts entry : cartToProductsSet){
            result += entry.getAmount() * entry.getProduct().getProductPrice();
        }
        this.setTotalPrice(result);
        return result;
    }
}
