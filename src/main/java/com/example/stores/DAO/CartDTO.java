package com.example.stores.DAO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartDTO {
    private long id;
    private Long user_id;
    private Long product_id;
    private int qty;
    public CartDTO(Long user_id, Long product_id){
        this.user_id=user_id;
        this.product_id=product_id;
    }

}
