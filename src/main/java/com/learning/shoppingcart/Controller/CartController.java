package com.learning.shoppingcart.Controller;

import com.learning.shoppingcart.Service.CartService;
import com.learning.shoppingcart.models.cart.requests.AddCartRequest;
import com.learning.shoppingcart.models.cart.response.CartResponse;
import com.learning.shoppingcart.models.cart.response.CartTotal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart")
    public ResponseEntity<CartResponse> addCart(@RequestBody AddCartRequest addCartRequest) {
        return ResponseEntity.ok(cartService.addToCart(addCartRequest));
    }

    @PostMapping("/cart/{cartId}/total")
    public ResponseEntity<CartTotal> getCartTotal(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.getCartTotal(cartId));
    }

}
