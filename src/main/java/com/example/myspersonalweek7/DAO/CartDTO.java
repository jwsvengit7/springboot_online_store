package com.example.myspersonalweek7.DAO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CartDTO {
    private long id;
    private int user_id;
    private String product_id;
    private int qty;

}
