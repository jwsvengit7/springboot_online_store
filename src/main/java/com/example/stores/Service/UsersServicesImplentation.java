package com.example.stores.Service;

import com.example.stores.DTO.UserDTO;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Repository.CartRepository;
import com.example.stores.Repository.UserRep.UserService;
import com.example.stores.Repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Random;

@Service
@Data

@RequiredArgsConstructor

public class UsersServicesImplentation implements  UserService {
    private final UsersRepository userRepository;
    private final CartRepository cartRepository;
    private final HttpServletRequest request;
    private final ModelMapper modelMapper;

    @Override
    public Object save(UserDTO userDTO) {
        HttpSession session = request.getSession();
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            throw  new RuntimeException("Error");
        } else {
            UsersModel use = checkIf(userDTO.getEmail());
            if (use != null) {
                System.out.println("Duplicate login");
                throw  new RuntimeException("Error");

            } else {
                Random random = new Random();
                Long randomNumber = random.nextLong(10);
                session.setAttribute("otp",randomNumber);
                session.setAttribute("userDto",userDTO);
                System.out.println(session.getAttribute("userDto"));
            }
        }
        return session.getAttribute("otp");
    }
    @Override
    public UserDTO validateUser(UserDTO userDTO){
        UsersModel usersModel = new UsersModel();
        usersModel.setUsername(userDTO.getUsername());
        usersModel.setPassword(userDTO.getPassword());
        usersModel.setEmail(userDTO.getEmail());
        System.out.println("Saved: " + usersModel);
        return modelMapper.map(userRepository.save(usersModel),UserDTO.class);
    }
    public UsersModel checkIf(String email) {
        return userRepository.findUsersModelByEmail(email).orElse(null);
    }
    @Override
    public UserDTO authenticate(UserDTO userDTO) {
        return modelMapper.map(userRepository.findUsersModelByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword()).orElse(null),UserDTO.class);
    }
    @Override
    public Model saveAllSession(Model model, UserDTO user, HttpServletRequest https, String status) {
        UserDTO check = authenticate(user);
        HttpSession session = https.getSession();
        session.setAttribute("session_id", check.getId());
        session.setAttribute("session_email", check.getEmail());
        model.addAttribute("session_id", session.getAttribute("session_id"));
        model.addAttribute("session_email", session.getAttribute("session_email"));
        model.addAttribute("status", status);
        System.out.println(session.getAttribute("session_id"));
        System.out.println(session.getAttribute("session_email"));
        return model;
    }


}
