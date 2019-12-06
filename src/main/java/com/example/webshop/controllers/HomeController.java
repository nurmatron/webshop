package com.example.webshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

@Controller
public class HomeController {

    @RequestMapping("/")
   public String index(Model model) {
        Object customer;





        return "index";



    }


}
