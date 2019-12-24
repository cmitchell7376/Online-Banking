package com.example.Online.Banking.Controllers;

import com.example.Online.Banking.Models.Account;
import com.example.Online.Banking.Models.Data.AccountData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class accountController {

    @RequestMapping(value = "account")
    public  String index(Model model){

        model.addAttribute("accounts", AccountData.getAll());
        model.addAttribute("title", "Users Accounts");

        if(AccountData.getAll().size() == 0){
            model.addAttribute("title", "Users Accounts");
            model.addAttribute("message","No Account Present!");
            return "account/index";
        }

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

        if(AccountData.moreThanFour() == true){
            model.addAttribute("title","Open Account Form");
            model.addAttribute("error2","User can't have more then four accounts.");
            return "account/addform";
        }

        AccountData.add(account);

        return "redirect:account";
    }

    @RequestMapping(value = "deposit",method = RequestMethod.GET)
    public String depositForm(Model model){

        if(AccountData.getAll().size() == 0){
            model.addAttribute("message","No Account Present!");
            return "account/noDeposit";
        }

        model.addAttribute("accounts",AccountData.getAll());
        model.addAttribute("title", "Deposit");
        return "account/deposit";
    }

    @RequestMapping(value = "deposit",method = RequestMethod.POST)
    public String processForm(Model model, @RequestParam String account, @RequestParam String amount){

        Account tmpAccount = AccountData.getByName(account);
        double newAmount = Double.parseDouble(amount);

        if(AccountData.negCheck(newAmount) == true){
            model.addAttribute("title", "Deposit");
            model.addAttribute("accounts",AccountData.getAll());
            model.addAttribute("message2","Amount number cannot be negative");
            return "account/deposit";
        }

        tmpAccount.depost(newAmount);

        return "redirect:account";
    }

    @RequestMapping(value = "withdraw",method = RequestMethod.GET)
    public String withdrawForm(Model model){

        if(AccountData.getAll().size() == 0){
            model.addAttribute("message","No Account Present!");
            return "account/noWithdraw";
        }

        model.addAttribute("accounts",AccountData.getAll());
        model.addAttribute("title", "Withdraw");
        return "account/withdraw";
    }

    @RequestMapping(value = "withdraw",method = RequestMethod.POST)
    public String processForm2(Model model, @RequestParam String account, @RequestParam String amount){

        Account tmpAccount = AccountData.getByName(account);
        double newAmount = Double.parseDouble(amount);

        if(AccountData.negCheck(newAmount) == true){
            model.addAttribute("title", "withdraw");
            model.addAttribute("accounts",AccountData.getAll());
            model.addAttribute("message2","Amount number cannot be negative");
            return "account/withdraw";
        }

        tmpAccount.withdrew(newAmount);

        return "redirect:account";
    }
}
