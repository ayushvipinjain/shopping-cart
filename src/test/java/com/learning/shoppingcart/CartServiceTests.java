package com.learning.shoppingcart;


import com.learning.shoppingcart.Repository.CartRepository;
import com.learning.shoppingcart.Repository.ProductRepository;
import com.learning.shoppingcart.Repository.UserRepository;
import com.learning.shoppingcart.Service.CartService;
import com.learning.shoppingcart.models.cart.requests.AddCartRequest;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CartServiceTests {

    @InjectMocks
    private CartService cartService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @Test
    public void TestUserNOtFoundWhileAddingItemInTheCart(){
        AddCartRequest addCartRequest = getAddCartRequest();
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class,()->cartService.addToCart(addCartRequest));
    }

    private AddCartRequest getAddCartRequest(){
        AddCartRequest addCartRequest = Mockito.mock(AddCartRequest.class);
        addCartRequest.setUserId(100);
        addCartRequest.setProductId(200);
        addCartRequest.setQuantity(10);
        return addCartRequest;
    }
}
