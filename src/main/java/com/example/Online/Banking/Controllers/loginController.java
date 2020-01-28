package com.example.Online.Banking.Controllers;

import com.example.Online.Banking.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class loginController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String Login(Model model){

        model.addAttribute("title","Please Login");

        return "HomePage/login";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String Registration(Model model){

        model.addAttribute("title","Create Account");
        model.addAttribute(new User());

        return "HomePage/registration";
    }
}

