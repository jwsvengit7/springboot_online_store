package com.example.myspersonalweek7.Service;

import com.example.stores.DAO.ProductDTO;
import com.example.stores.Entity.Product;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Repository.ProductRepository;
import com.example.stores.Repository.UsersRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class AdminService  {

    private final UsersRepository userRepository;
    private final ProductRepository productRepository;


    public Long findsUserLength(){
        return userRepository.count();
    }
    public Long findsProductLength(){
        return productRepository.count();
    }
    public List<Product> allProductJson(){
        return productRepository.findAll();
    }
    public List<UsersModel> allUser(){
        return userRepository.findAll();
    }
    public boolean saveProduct(ProductDTO productDTO){
        boolean status = false;
        if (productDTO.getName().isEmpty()){
            status=false;

        }else{
            Product product = new Product();
            product.setQty(productDTO.getQty());
            product.setName(productDTO.getName());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());
            if (productDTO.getCategory().equals("Foods") && productDTO.getName().equals("Soup")) {
                product.setImage("soup.jpeg");
            } else if (productDTO.getCategory().equals("Accessories") && productDTO.getName().equals("Printer")) {
                product.setImage("Printer.jpeg");
            } else if (productDTO.getCategory().equals("Kitchen Kit") && productDTO.getName().equals("ELECTRIC BREAD TOASTER")) {
                product.setImage("Bread.jpeg");
            } else if (productDTO.getCategory().equals("Kitchen Kit") && productDTO.getName().equals("3 SETS OF NONSTICK POTS")) {
                product.setImage("sh.jpeg");
            } else if (productDTO.getCategory().equals("Kitchen Kit") && productDTO.getName().equals("Stove")) {
                product.setImage("st.jpeg");
            } else if (productDTO.getCategory().equals("Kitchen Kit") && productDTO.getName().equals("Pot")) {
                product.setImage("things.jpeg");
            } else if (productDTO.getCategory().equals("Foods") && productDTO.getName().equals("Burger")) {
                product.setImage("Burger.jpeg");
            } else if (productDTO.getCategory().equals("Accessories") && productDTO.getName().equals("HP 19.5 VOLTS ADAPTER")) {
                product.setImage("charget.PNG");
            }
            productRepository.save(product);
            status=true;


        }
        return status;

    }
}
