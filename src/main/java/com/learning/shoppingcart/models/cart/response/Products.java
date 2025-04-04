package com.learning.shoppingcart.models.cart.response;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.math.BigDecimal;

@Getter
@Setter
public class Products {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;
}
