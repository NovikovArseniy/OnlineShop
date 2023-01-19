package ru.novikov.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderID")
    Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotBlank(message = "City is required")
    @Column(name = "City")
    private String city;

    @NotBlank(message = "Address is required")
    @Column(name = "Address")
    private String address;

    @Column(name = "PlacedAt")
    private Date placedAt;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    Set<OrdersToProducts> ordersToProductsSet;

    @CreditCardNumber(message="Not a valid credit card number")
    @Transient
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    @Transient
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Transient
    private String ccCVV;

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }

    public int getTotalPrice(){
        int result = 0;
        for (OrdersToProducts entry : ordersToProductsSet){
            result += entry.getAmount() * entry.getProduct().getProductPrice();
        }
        return result;
    }
}
