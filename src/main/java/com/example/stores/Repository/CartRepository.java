package com.example.stores.Repository;

import com.example.stores.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
   @Query(value = "SELECT * FROM cart WHERE user_id=?1 and product_id=?2",nativeQuery = true)
   List<Cart> findQueryForCart(Long user_id,Long product_id);
   @Query(value = "SELECT COUNT(*) FROM cart WHERE user_id=?1",nativeQuery = true)
   Long findCart(Long userid);

   @Query(value = "SELECT * FROM cart WHERE user_id=?1",nativeQuery = true)
   List<Cart> finds(Long userid);



}
