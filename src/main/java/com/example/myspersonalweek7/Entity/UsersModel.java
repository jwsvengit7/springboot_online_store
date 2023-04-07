package com.example.myspersonalweek7.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "signup")
@Data
@NoArgsConstructor

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

    public UsersModel(String username,String email,String password){
        this.email=email;
        this.username=username;
        this.password=password;

    }



}
