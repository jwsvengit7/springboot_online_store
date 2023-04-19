package com.example.stores.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class AdminDTO {
    private Long id;
    private String email;
    private String password;


}
