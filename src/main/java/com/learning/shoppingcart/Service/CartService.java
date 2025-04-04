package com.learning.shoppingcart.Service;

import com.learning.shoppingcart.Entity.Cart;
import com.learning.shoppingcart.Entity.CartItems;
import com.learning.shoppingcart.Entity.ProductDetails;
import com.learning.shoppingcart.Entity.UserDetails;
import com.learning.shoppingcart.Repository.CartItemsRepository;
import com.learning.shoppingcart.Repository.CartRepository;
import com.learning.shoppingcart.Repository.ProductRepository;
import com.learning.shoppingcart.Repository.UserRepository;
import com.learning.shoppingcart.models.cart.requests.AddCartRequest;
import com.learning.shoppingcart.models.cart.response.CartResponse;
import com.learning.shoppingcart.models.cart.response.CartTotal;
import com.learning.shoppingcart.models.cart.response.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    BigDecimal taxRate = BigDecimal.valueOf(0.05);

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

    public CartTotal getCartTotal(Long id) {

        List<CartItems> cartItems = cartItemsRepository.findByCartCartId(id);

        CartTotal cartTotal = new CartTotal();
        List<Products> products = new ArrayList<>();
        cartItems.forEach(cartItem -> {
            Products productDetails = new Products();
            productDetails.setProductId(cartItem.getProductDetails().getProductId());
            productDetails.setProductName(cartItem.getProductDetails().getName());
            productDetails.setQuantity(cartItem.getProductDetails().getQuantity());
            productDetails.setSubtotal(cartItem.getTotalPrice());
            products.add(productDetails);
        });

        BigDecimal subtotal = cartItems.stream()
                .map(CartItems::getTotalPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        BigDecimal tax = subtotal.multiply(taxRate);

        BigDecimal total = tax.add(subtotal);

        cartTotal.setTax(tax);
        cartTotal.setSubtotal(subtotal);
        cartTotal.setProducts(products);
        cartTotal.setTotal(total);
        return cartTotal;
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

