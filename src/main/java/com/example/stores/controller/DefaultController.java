package com.example.stores.controller;
import com.example.stores.DAO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DefaultController {
    @GetMapping("/")
    public ModelAndView HomePage(ModelAndView model,HttpServletRequest request) {
        return redirectSession("index",request,model);
    }
    @GetMapping("/signup")
    public ModelAndView signup(ModelAndView model,HttpServletRequest request) {
        return redirectSession("signup",request,model);
    }
    @GetMapping("/sign-out")
    public String Logout(ModelAndView model,HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("error_request", new UserDTO());
        return "error_page";
    }


    public ModelAndView redirectSession(String redirect,HttpServletRequest request,ModelAndView model){
        HttpSession session =request.getSession();
        if (session.getAttribute("session_email") == null || session.getAttribute("session_id") == null) {
            model.setViewName(redirect);
            model.addObject("user", new UserDTO());
        } else {
            model.setViewName("redirect:/dashboard");
        }
        return model;
    }


}
