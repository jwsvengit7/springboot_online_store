package com.example.myspersonalweek7.controller;
import com.example.stores.DAO.AdminDTO;
import com.example.stores.DAO.ProductDTO;
import com.example.stores.Entity.Product;
import com.example.stores.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private  AdminService adminService;

    @GetMapping("/admin")
    public ModelAndView admin(ModelAndView modelAndView) {
        return setAdminModelAndView("Admin/index",modelAndView);
    }

    @GetMapping("/admin/view-product")
    public ModelAndView view_product(ModelAndView modelAndView) {

        return setAdminModelAndView("Admin/view_details",modelAndView);

    }
    @GetMapping("/admin/add-product")
    public ModelAndView add_product(ModelAndView modelAndView) {
        return setAdminModelAndView("Admin/upload_product",modelAndView);
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
    public ModelAndView createProduct(Product product,ModelAndView model){
        if(adminService.saveProduct(new ProductDTO(product.getName(),product.getCategory(),product.getImage(),product.getPrice(),
                product.getQty()))){
            model.setViewName("redirect:admin/add-product");
        }else{
            model.setViewName("redirect:error_page");
        }
        return model;


    }

}
