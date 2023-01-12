package ru.novikov.shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderID")
    int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    /*
    @NotBlank(message = "Name is required")
    @Column(name = "CustomerName")
    private String customerName;


    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", message = "Must be a valid phone number starting with +7")
    @Column(name = "PhoneNumber")
    private String phoneNumber;
     */
    @NotBlank(message = "City is required")
    @Column(name = "City")
    private String city;

    @NotBlank(message = "Address is required")
    @Column(name = "Address")
    private String address;

    @Column(name = "PlacedAt")
    private Date placedAt;

    @OneToMany(mappedBy = "order")
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
}
