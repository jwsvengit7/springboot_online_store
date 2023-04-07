package com.example.myspersonalweek7.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
    @Table(name = "product")

    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @Column
        String name;
        @Column
        String price;
        @Column
        int qty;
        @Column
        String image;
        @Column
        BigDecimal user_id;
        @Column
        String category;
        }

