package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

   Optional<Cart> findByUserDetailsUserId(Long userId);
}
