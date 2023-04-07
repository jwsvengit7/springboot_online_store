package com.example.myspersonalweek7.controller;

import com.example.stores.DAO.CartDTO;
import com.example.stores.DAO.UserDTO;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Service.CartService;
import com.example.stores.Service.UsersServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersServices usersService;
    private final CartService cartRep;


    @PostMapping("/sign-up")
    public String register(@ModelAttribute Model model,UserDTO userDTO, HttpServletRequest httpServletRequest) {
        boolean registeredUser = usersService.save(userDTO);
        if (registeredUser) {
            usersService.saveAllSession(model,userDTO,httpServletRequest,"successful signup");
            return "redirect:dashboard";
        } else {
            model.addAttribute("status", "Email Already Exist");
            return "redirect:error";
        }
    }
    @PostMapping("/login")
    public String login(@Validated UserDTO userDTO, Model model, HttpServletRequest httpServletRequest) {
        System.out.println("login request: " + userDTO);
        UsersModel authenticated = usersService.authenticate(userDTO);
        if (authenticated != null){
            usersService.saveAllSession(model,userDTO,httpServletRequest,"successful login");
            return "redirect:dashboard";
        } else {
            return "redirect:error";
        }
    }

    @GetMapping("/dashboard")
    public ModelAndView Dashboard(ModelAndView model, HttpSession session) {

        if (session.getAttribute("session_email") == null && session.getAttribute("session_id") == null) {
            model.setViewName("redirect:/");
        } else {
            Long itemCount = cartRep.countCart(Integer.parseInt(session.getAttribute("session_id").toString()));
            model.addObject("itemCount", itemCount);
            model.addObject("listOfProduct8",usersService.listsLimit());
            model.addObject("cart", new CartDTO());
            model.addObject("session_id", session.getAttribute("session_id"));
            model.addObject("session_email", session.getAttribute("session_email"));
            model.setViewName("User/index");
        }
        return model;
    }

    @GetMapping("/shop")
    public ModelAndView shop(HttpSession session,ModelAndView model){
        if (session.getAttribute("session_email") == null && session.getAttribute("session_id") == null) {
            model.setViewName("redirect:/");
        } else {
            Long itemCount = cartRep.countCart(Integer.parseInt(session.getAttribute("session_id").toString()));
            model.addObject("itemCount", itemCount);
            model.addObject("listOfProduct",usersService.listsAll());
            model.addObject("cart", new CartDTO());
            model.addObject("session_email", session.getAttribute("session_email"));
            model.addObject("session_id", session.getAttribute("session_id"));
            model.setViewName("User/shop");
        }
        return model;
    }

    @GetMapping("/cart/{userId}")
    public String viewCart(@PathVariable("userId") int userId,ModelAndView model,HttpSession session){
        Long itemCount = cartRep.countCart(userId);
        model.addObject("itemCount", itemCount);
        model.addObject("session_email", session.getAttribute("session_email"));
        model.addObject("session_id", session.getAttribute("session_id"));
        return "User/cart";
    }

    @PostMapping("/addcart")
    public String addCart(@RequestParam("user_id") String userId, @RequestParam("product_id") String productId, CartDTO cartDTO) {
        cartDTO.setUser_id(Integer.parseInt(userId));
        cartDTO.setProduct_id(productId);
        if (cartRep.increaseCartOrAddCart(cartDTO) == 1) {
            return "redirect:/cart/" + userId;
        } else {
            return "redirect:/cart?error=1";
        }
    }



}
