package com.learning.shoppingcart.Repository;

import com.learning.shoppingcart.Entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
}
