package com.learning.shoppingcart.models.cart.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartTotal {
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal total;
    private List<Products> products;
}
