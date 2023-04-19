package com.example.stores.DAO;

import com.example.stores.Entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private int price;
    private String image;
    private int qty;
    private CartDTO cartDTO;
    private Product product;

    public ProductDTO(Long id,String name,String category,String image,int price,int qty) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.image = image;

    }
    public ProductDTO(String name,String category,String image,int price,int qty) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.qty = qty;
        this.image = image;

    }
    public ProductDTO(String image,String name,String category,int price) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;

    }


    public ProductDTO(Product updatedProduct) {
        this.product=updatedProduct;
    }
}
