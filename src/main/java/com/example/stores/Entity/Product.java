package com.example.stores.Entity;

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
        int price;
        @Column
        int qty;
        String image;
        @Column
        BigDecimal user_id;
        @Column
        String category;
        }

