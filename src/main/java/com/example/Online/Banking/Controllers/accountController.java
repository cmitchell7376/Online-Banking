package com.example.Online.Banking.Controllers;

import com.example.Online.Banking.Models.Account;
import com.example.Online.Banking.Models.Data.AccountData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class accountController {

    @RequestMapping(value = "account")
    public  String index(Model model){

        model.addAttribute("accounts", AccountData.getAll());
        model.addAttribute("title", "Users Accounts");

        return "account/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String accountForm(Model model){

        model.addAttribute("title","Open Account Form");
        model.addAttribute(new Account());

        return "account/addform";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String accountProcess(Model model, @ModelAttribute @Valid Account account, Errors errors){

        if(errors.hasErrors()){
            model.addAttribute("title","Open Account Form");
            return "account/addform";
        }

        AccountData.add(account);

        return "redirect:account";
    }
}
