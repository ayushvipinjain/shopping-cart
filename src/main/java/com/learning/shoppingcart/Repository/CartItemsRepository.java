package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}
