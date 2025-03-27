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


    public CartResponse addToCart(AddCartRequest addCartRequest){

       UserDetails userDetails = userRepository.findById(addCartRequest.getUserId())
               .orElseThrow(()-> new RuntimeException("User Not Found"));

      ProductDetails productDetails=  productRepository.findById(addCartRequest.getProductId().longValue())
               .orElseThrow(()-> new RuntimeException("Product Not Found"));

       Cart cart = cartRepository.findByUserDetailsUserId(userDetails.getUserId())
               .orElseGet(()-> createNewCart(userDetails));

      List<CartItems> cartItems=  cartItemsRepository.findByCartCartIdAndProductDetailsProductId(cart.getCartId(),productDetails.getProductId());

      if(cartItems.isEmpty()){
          CartItems newCartItem = new CartItems();
          newCartItem.setCart(cart);
          newCartItem.setQuantity(addCartRequest.getQuantity());
          newCartItem.setProductDetails(productDetails);
          newCartItem.setTotalPrice(productDetails.getAmount().multiply(BigDecimal.valueOf(addCartRequest.getQuantity())));
          cartItems.add(newCartItem);
          cartItemsRepository.save(newCartItem);
      }
      else {
         // TODO - If the Cart Items Exist update the  Quantity and Total Amount
      }

        // TODO - Update Cart with the Cart Items

        // TODO - Save the Latest Cart in DB

        // TODO - Transform the response to the Response DTO and return Cart Response

        return  null;
    }

    private Cart createNewCart(UserDetails userDetails){
        Cart cart = new Cart();
        cart.setUserDetails(userDetails);
        cart.setTaxRate(taxRate);
        cart.setSubTotal(BigDecimal.ZERO);
        cart.setTotal(BigDecimal.ZERO);
        return cartRepository.save(cart);
    }
}

