package com.example.stores.Repository.Products;

import com.example.stores.DAO.ProductDTO;
import com.example.stores.Entity.Product;

import java.util.List;

public interface ProductRep {
    List<Product> listsLimit();
    List<Product> listsAll();
    List<ProductDTO> getProductById(Long id);
}
