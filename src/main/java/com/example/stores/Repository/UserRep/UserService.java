package com.example.stores.Repository.UserRep;

import com.example.stores.DAO.UserDTO;
import com.example.stores.Entity.UsersModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface UserService {
    boolean save(UserDTO userDTO);
    UsersModel authenticate(UserDTO userDTO);
     Model saveAllSession(Model model,UserDTO userDTO, HttpServletRequest https, String status);



}