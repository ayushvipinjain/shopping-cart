package com.learning.shoppingcart.Service;

import com.learning.shoppingcart.Entity.Cart;
import com.learning.shoppingcart.Entity.CartItems;
import com.learning.shoppingcart.Entity.ProductDetails;
import com.learning.shoppingcart.Entity.UserDetails;
import com.learning.shoppingcart.Repository.CartItemsRepository;
import com.learning.shoppingcart.Repository.CartRepository;
import com.learning.shoppingcart.Repository.ProductRepository;
import com.learning.shoppingcart.Repository.UserRepository;
import com.learning.shoppingcart.models.cart.AddCartRequest;
import com.learning.shoppingcart.models.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    BigDecimal taxRate = BigDecimal.valueOf(5.00);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;


    public CartResponse addToCart(AddCartRequest addCartRequest) {

        UserDetails userDetails = userRepository.findById(addCartRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        ProductDetails productDetails = productRepository.findById(addCartRequest.getProductId().longValue())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        Cart cart = cartRepository.findByUserDetailsUserId(userDetails.getUserId())
                .orElseGet(() -> createNewCart(userDetails));

        Optional<CartItems> cartItems = cartItemsRepository.findByCartCartIdAndProductDetailsProductId(cart.getCartId(), productDetails.getProductId());

        if (cartItems.isEmpty()) {
            // Generate the cart item if not present
            CartItems myCartItems = new CartItems();
            myCartItems.setCart(cart);
            myCartItems.setQuantity(addCartRequest.getQuantity());
            myCartItems.setProductDetails(productDetails);
            myCartItems.setTotalPrice(productDetails.getAmount().multiply(BigDecimal.valueOf(addCartRequest.getQuantity())));
            cartItemsRepository.save(myCartItems);
        } else {
            // If the Cart Items Exist update the  Quantity and Total Amount
            CartItems existingCartItem = cartItems.get();
            existingCartItem.setQuantity(existingCartItem.getQuantity() + addCartRequest.getQuantity());
            existingCartItem.setTotalPrice(existingCartItem.getProductDetails().getAmount().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemsRepository.save(existingCartItem);
        }

        CartResponse cartResponse = new CartResponse();
        cartResponse.setCartId(cart.getCartId());

        return cartResponse;
    }

    private Cart createNewCart(UserDetails userDetails) {
        Cart cart = new Cart();
        cart.setUserDetails(userDetails);
        cart.setTaxRate(taxRate);
        cart.setSubTotal(BigDecimal.ZERO);
        cart.setTotal(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }
}

