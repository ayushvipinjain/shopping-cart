package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

    List<CartItems> findByCartCartIdAndProductDetailsProductId(Long cart_id, Long product_id);

}
