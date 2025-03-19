package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,Long> {
}
