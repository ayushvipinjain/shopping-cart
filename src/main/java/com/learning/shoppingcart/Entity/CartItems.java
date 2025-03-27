package com.learning.shoppingcart.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long cartItemId;

    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "product_id")
    private ProductDetails productDetails ;

    private Integer quantity;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;
}
