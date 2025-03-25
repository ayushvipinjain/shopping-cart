package com.learning.shoppingcart.Service;

import com.learning.shoppingcart.Repository.CartItemsRepository;
import com.learning.shoppingcart.Repository.CartRepository;
import com.learning.shoppingcart.Repository.UserRepository;
import com.learning.shoppingcart.models.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartResponse addToCart(String userId){
        return  null;
    }

}

