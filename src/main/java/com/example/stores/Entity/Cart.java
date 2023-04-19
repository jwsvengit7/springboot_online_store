package com.example.stores.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    Long product_id;
    @Column
    int qty;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private UsersModel user;


}
