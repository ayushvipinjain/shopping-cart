package com.learning.shoppingcart.Controller;

import com.learning.shoppingcart.Entity.Cart;
import com.learning.shoppingcart.Service.CartService;
import com.learning.shoppingcart.models.cart.AddCartRequest;
import com.learning.shoppingcart.models.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<CartResponse> addCart(@RequestBody AddCartRequest addCartRequest) {
        return ResponseEntity.ok(cartService.addToCart(addCartRequest));
    }

}
