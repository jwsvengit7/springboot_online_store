package com.example.myspersonalweek7.DAO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class AdminDTO {
    private Long id;
    private String email;
    private String password;


}
