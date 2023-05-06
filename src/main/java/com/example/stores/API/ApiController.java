package com.example.stores.API;

import com.example.stores.Entity.Product;
import com.example.stores.Entity.UsersModel;
import com.example.stores.Service.AdminServiceImplentation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@RestController
public class ApiController {


    private AdminServiceImplentation adminServiceImplentation;

    @GetMapping("/api-users")
    public List<UsersModel> getUsers() {
        return adminServiceImplentation.allUser();
    }

    @GetMapping("/api-product")
    public List<Product> getAllProduct() {
        return adminServiceImplentation.allProductJson();
    }
    @GetMapping("/user-length")
    public Long countUser(){
        return adminServiceImplentation.findsUserLength();
    }
    @GetMapping("/product-length")
    public Long countProduct(){
        return adminServiceImplentation.findsProductLength();
    }

    @GetMapping("/api")
    public ModelAndView api() {
        String url = "https://api.coingecko.com/api/v3/coins/";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        ModelAndView modelAndView = new ModelAndView("User/api");
        modelAndView.addObject("responseBody", responseBody);
        return modelAndView;
    }

}
