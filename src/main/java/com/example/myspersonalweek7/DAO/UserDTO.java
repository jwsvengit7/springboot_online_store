package com.example.myspersonalweek7.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserDTO(String username,String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public UserDTO(String email,String password) {

        this.email = email;
        this.password = password;
    }

}