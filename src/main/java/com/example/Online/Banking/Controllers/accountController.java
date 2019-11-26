package com.example.Online.Banking.Controllers;

import com.example.Online.Banking.Models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class accountController {

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String accountForm(Model model){
        model.addAttribute("title","Open Account Form");
        model.addAttribute(new Account());

        return "account/addform";
    }
}
