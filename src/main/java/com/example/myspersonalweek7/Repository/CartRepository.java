package com.example.myspersonalweek7.Repository;

import com.example.stores.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
   @Query(value = "SELECT * FROM cart WHERE user_id=?1 and product_id=?2",nativeQuery = true)
   List<Cart> findQueryForCart(int user_id,String product_id);
   @Query(value = "SELECT COUNT(*) FROM cart WHERE user_id=?1",nativeQuery = true)
   int findCart(int userid);



}
