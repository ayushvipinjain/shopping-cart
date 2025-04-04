package com.learning.shoppingcart.models.cart.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartRequest {
    long userId;
    Integer productId;
    Integer quantity;
}
