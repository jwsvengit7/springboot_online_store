package com.example.stores.Entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "signup")



public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String username;
    @Column
    String email;
    @Column
    String password;


}
