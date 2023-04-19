package com.example.stores.controller;

import com.example.stores.DAO.AdminDTO;
import com.example.stores.DAO.ProductDTO;
import com.example.stores.Entity.Product;
import com.example.stores.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView modelAndView) {
        return setAdminModelAndView("Admin/index",modelAndView);
    }

    @GetMapping("/admin/view-product")
    public ModelAndView view_product(ModelAndView modelAndView) {
        modelAndView.addObject("all",new Product());
        return setAdminModelAndView("Admin/view_details",modelAndView);

    }
    @GetMapping("/admin/add-product")
    public ModelAndView add_product(ModelAndView modelAndView) {
        modelAndView.addObject("products", new ProductDTO());
        modelAndView.setViewName("Admin/upload_product");
        return modelAndView;
    }
    private ModelAndView setAdminModelAndView(String view,ModelAndView modelAndView) {
        modelAndView.setViewName(view);
        modelAndView.addObject("adminUser", new AdminDTO());
        modelAndView.addObject("products", new ProductDTO());
        if(view=="Admin/view_details" || view=="Admin/index" ){
            modelAndView.setViewName(view);
            modelAndView.addObject("adminUser", new AdminDTO());
            modelAndView.addObject("countUser", adminService.findsUserLength());
            modelAndView.addObject("listOfUsers", adminService.allUser());
            modelAndView.addObject("listOfProduct", adminService.allProductJson());
            modelAndView.addObject("countProduct", adminService.findsProductLength());
        }

        return modelAndView;
    }

    @PostMapping("/upload_product")
    public ModelAndView createProduct(@ModelAttribute Product product ,MultipartFile images, ModelAndView model) throws IOException {
        if(adminService.saveProduct(product,images)){
            model.setViewName("redirect:admin/add-product");
        }else{
            model.setViewName("redirect:error_page");
        }
        return model;
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam("id") Long id,
                                @RequestParam("name") String name,
                                @RequestParam("price") Integer price,
                                @RequestParam("category") String category,
                                @RequestParam("qty") Integer qty) {
        Product productToUpdate = adminService.getProductById(id);
        productToUpdate.setName(name);
        productToUpdate.setPrice(price);
        productToUpdate.setCategory(category);
        productToUpdate.setQty(qty);
        adminService.updateProduct(productToUpdate);
        return "redirect:/admin/view-product";
    }
    @PostMapping("/delete")
    public String delete(){
        return "redirect:/";
    }

}
