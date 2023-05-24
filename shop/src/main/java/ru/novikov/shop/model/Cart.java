package ru.novikov.shop.model;

import com.zaxxer.hikari.util.ConcurrentBag;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "carts")
@EqualsAndHashCode
public class Cart {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private Integer totalPrice;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    Set<CartToProducts> cartToProductsSet;

    @Transactional
    public int calculateAndSetTotalPrice(){
        int result = 0;
        System.out.println("!!!!!!!!!!!!!!!!!!calc!!!!!!!!!!!!!!!!!!");
        if (cartToProductsSet != null && !cartToProductsSet.isEmpty()) {
            for (CartToProducts entry : cartToProductsSet) {
                result += entry.getAmount() * entry.getProduct().getProductPrice();
            }
        }
        this.setTotalPrice(result);
        return result;
    }
}
