package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    Optional<CartItems> findByCartCartIdAndProductDetailsProductId(Long cart_id, Long product_id);

    List<CartItems> findByCartCartId(Long cart_id);
}
