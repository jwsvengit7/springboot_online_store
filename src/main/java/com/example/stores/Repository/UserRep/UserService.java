package com.example.stores.Repository.UserRep;

import com.example.stores.DTO.UserDTO;
import com.example.stores.Entity.UsersModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface UserService {
    Object save(UserDTO userDTO);
    UserDTO authenticate(UserDTO userDTO);
    UserDTO validateUser(UserDTO userDTO);
    Model saveAllSession(Model model,UserDTO userDTO, HttpServletRequest https, String status);



}