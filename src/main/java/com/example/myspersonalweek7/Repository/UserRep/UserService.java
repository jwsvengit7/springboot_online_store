package com.example.myspersonalweek7.Repository.UserRep;
import com.example.stores.DAO.UserDTO;
import com.example.stores.Entity.Product;
import com.example.stores.Entity.UsersModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService {
    boolean save(UserDTO userDTO);
    UsersModel authenticate(UserDTO userDTO);

    Model saveAllSession(Model model,UserDTO userDTO, HttpServletRequest https, String status);

    List<Product> listsLimit();
    List<Product> listsAll();
}