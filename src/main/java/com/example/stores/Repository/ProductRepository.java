package com.example.stores.Repository;

import com.example.stores.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT * FROM product LIMIT 8", nativeQuery = true)
    List<Product> findByLimit();
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.category = ?1, p.name = ?2, p.qty = ?3, p.price = ?4 WHERE p.id = ?5")

    int findProductById(String category,String name,String qty,Integer price,Long id);

    Product findProductById(Long productId);
}
