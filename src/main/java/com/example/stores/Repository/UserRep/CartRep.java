package com.example.stores.Repository.UserRep;

import com.example.stores.DTO.CartDTO;

public interface CartRep {
    int increaseCartOrAddCart(CartDTO cartDTO);
    Long countCart(Long user_id);

}
