package com.example.stores.controller;

import com.example.stores.DTO.CartDTO;
import com.example.stores.DTO.UserDTO;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Service.CartService;
import com.example.stores.Service.ProductService;
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
    private final ProductService productService;


    @PostMapping("/sign-up")
    public String register(@ModelAttribute UserDTO userDTO,Model model,HttpServletRequest httpServletRequest) {
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
            Long itemCount = cartRep.countCart(Long.parseLong(session.getAttribute("session_id").toString()));
            model.addObject("itemCount", itemCount);
            model.addObject("listOfProduct8",productService.listsLimit());
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
            Long itemCount = cartRep.countCart(Long.parseLong(session.getAttribute("session_id").toString()));
            model.addObject("itemCount", itemCount);
            model.addObject("listOfProduct",productService.listsAll());
            model.addObject("cart", new CartDTO());
            model.addObject("session_email", session.getAttribute("session_email"));
            model.addObject("session_id", session.getAttribute("session_id"));
            model.setViewName("User/shop");
        }
        return model;
    }

    @GetMapping("/cart/{userId}")
    public ModelAndView viewCart(@PathVariable("userId") Long userId,ModelAndView model,HttpSession session){
        Long itemCount = cartRep.countCart(userId);

        Long sess = (Long) session.getAttribute("session_id");

        model.addObject("itemCount", itemCount);
        model.addObject("session_email", session.getAttribute("session_email"));
        model.addObject("session_id", session.getAttribute("session_id"));
        model.addObject("cart",productService.getProductById(userId));
        model.setViewName("User/cart");
        //model.addObject("cartList",usersService.getProductById(userId));
        return model;
    }

    @PostMapping("/addcart")
    public String addCart(@RequestParam("user_id") Long userId, @RequestParam("product_id") String productId, CartDTO cartDTO) {
        cartDTO.setUser_id(userId);
        cartDTO.setProduct_id(Long.valueOf(productId));
        if (cartRep.increaseCartOrAddCart(cartDTO) == 1) {
            return "redirect:/cart/" + userId;
        } else {
            return "redirect:/cart?error=1";
        }
    }



}
