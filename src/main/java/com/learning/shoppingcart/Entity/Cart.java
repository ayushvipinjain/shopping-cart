package com.learning.shoppingcart.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CartItems> cartItems = new ArrayList<>();

    @Column(nullable = false,name = "sub_total")
    private BigDecimal subTotal = BigDecimal.ZERO;

    @Column(nullable = false,name = "tax_rate")
    private BigDecimal taxRate = BigDecimal.ZERO;

    @Column(nullable = false,name = "total")
    private BigDecimal total = BigDecimal.ZERO;
}


