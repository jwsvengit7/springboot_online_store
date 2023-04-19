package com.example.stores.Service;

import com.example.stores.DAO.UserDTO;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Repository.CartRepository;
import com.example.stores.Repository.UserRep.UserService;
import com.example.stores.Repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Data

@RequiredArgsConstructor

public class UsersServices implements  UserService {
    private final UsersRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public boolean save(UserDTO userDTO) {
        boolean status;
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            status = false;
        } else {
            UsersModel use = checkIf(userDTO.getEmail());
            if (use != null) {
                System.out.println("Duplicate login");
                status = false;
            } else {
                UsersModel usersModel = new UsersModel();
                usersModel.setUsername(userDTO.getUsername());
                usersModel.setPassword(userDTO.getPassword());
                usersModel.setEmail(userDTO.getEmail());
                System.out.println("Saved: " + usersModel);
                userRepository.save(usersModel);
                status = true;
            }
        }
        return status;
    }
    public UsersModel checkIf(String email) {
        return userRepository.findUsersModelByEmail(email).orElse(null);
    }
    @Override
    public UsersModel authenticate(UserDTO userDTO) {
        return userRepository.findUsersModelByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword()).orElse(null);
    }
    @Override
    public Model saveAllSession(Model model, UserDTO user, HttpServletRequest https, String status) {
        UsersModel check = authenticate(user);
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
