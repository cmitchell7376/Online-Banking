package com.example.Online.Banking.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @RequestMapping(value = "home")
    public String index(Model model){
        return "HomePage/home";
    }
}
