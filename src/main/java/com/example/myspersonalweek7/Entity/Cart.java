package com.example.myspersonalweek7.Entity;

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
    String product_id;
    @Column
    int user_id;
    @Column
    int qty;
}
