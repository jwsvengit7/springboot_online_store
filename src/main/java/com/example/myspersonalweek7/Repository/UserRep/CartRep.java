package com.example.myspersonalweek7.Repository.UserRep;

import com.example.stores.DAO.CartDTO;

public interface CartRep {
    int increaseCartOrAddCart(CartDTO cartDTO);
    long countCart(int user_id);
}
