package com.example.Online.Banking.Controllers;

import com.example.Online.Banking.Models.Account;
import com.example.Online.Banking.Models.Data.AccountData;
import com.example.Online.Banking.Models.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Iterator;

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
            model.addAttribute("title", "Deposit");
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

        Transaction transaction = new Transaction("Deposit",tmpAccount.getBalance(),newAmount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(tmpAccount);
        tmpAccount.addTransaction(transaction);

        tmpAccount.depost(newAmount);

        return "redirect:account";
    }

    @RequestMapping(value = "withdraw",method = RequestMethod.GET)
    public String withdrawForm(Model model){

        if(AccountData.getAll().size() == 0){
            model.addAttribute("message","No Account Present!");
            model.addAttribute("title", "Withdraw");
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

        Transaction transaction = new Transaction("Withdraw",tmpAccount.getBalance(),newAmount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(tmpAccount);
        tmpAccount.addTransaction(transaction);

        tmpAccount.withdrew(newAmount);

        return "redirect:account";
    }

    @RequestMapping(value = "transfer",method = RequestMethod.GET)
    public String transferForm(Model model){

        if(AccountData.getAll().size() == 0){
            model.addAttribute("message","No Account Present!");
            model.addAttribute("title", "Transfer");
            return "account/noTransfer";
        }

        if(AccountData.getAll().size() == 1){
            model.addAttribute("message","Must Have More Than One Account!");
            model.addAttribute("title", "Transfer");
            return "account/noTransfer";
        }

        model.addAttribute("accounts",AccountData.getAll());
        model.addAttribute("title", "Transfer");
        return "account/transfer";
    }

    @RequestMapping(value = "transfer",method = RequestMethod.POST)
    public String transferForm(Model model, @RequestParam String account, @RequestParam String account2,
                               @RequestParam String amount){

        Account tmpAccount = AccountData.getByName(account);
        Account tmpAccount2 = AccountData.getByName(account2);
        double newAmount = Double.parseDouble(amount);

        if(AccountData.negCheck(newAmount) == true){
            model.addAttribute("title", "Transfer");
            model.addAttribute("accounts",AccountData.getAll());
            model.addAttribute("message2","Amount number cannot be negative");
            return "account/transfer";
        }

        if (account.equalsIgnoreCase(account2)) {
            model.addAttribute("title", "Transfer");
            model.addAttribute("accounts",AccountData.getAll());
            model.addAttribute("message2","Can Not Transfer Into Same Account");
            return "account/transfer";
        }

        Transaction transaction = new Transaction("Withdraw",tmpAccount.getBalance(),newAmount);
        transaction.setTime(LocalDate.now());
        transaction.setAccount(tmpAccount);
        tmpAccount.addTransaction(transaction);

        Transaction transaction2 = new Transaction("Deposit",tmpAccount2.getBalance(),newAmount);
        transaction2.setTime(LocalDate.now());
        transaction2.setAccount(tmpAccount2);
        tmpAccount.addTransaction(transaction2);

        tmpAccount.withdrew(newAmount);
        tmpAccount2.depost(newAmount);

        return "redirect:account";
    }

    @RequestMapping(value = "close",method = RequestMethod.GET)
    public String closeForm(Model model){

        if(AccountData.getAll().size() == 0){
            model.addAttribute("message","No Account Present!");
            model.addAttribute("title", "Close Account");
            return "account/noClose";
        }

        model.addAttribute("accounts",AccountData.getAll());
        model.addAttribute("title", "Close Account");
        return "account/close";
    }

    @RequestMapping(value = "confirm", method = RequestMethod.POST)
    public String confirmprocess(Model model, @RequestParam String account){
        model.addAttribute("text", "Are you sure you want to delete account");
        model.addAttribute("accountName", account);
        return "account/confirm";
    }

    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public String answerProcess(Model model, @RequestParam String accountName,
                                @RequestParam(value = "no", required = false) String no){
        String answer = no;
        if(answer == null){
            for(Iterator<Account> it = AccountData.getAll().iterator(); it.hasNext();){
                Account account = it.next();
                if(account.getName().equalsIgnoreCase(accountName)){
                    it.remove();
                }
            }
        }

        return "redirect:account";
    }

    @RequestMapping(value = "accounts", method = RequestMethod.GET)
    public String accounts(Model model, @RequestParam int id){

        Account account = AccountData.getById(id);
        if(account.getTransactionList().size() == 0){
            model.addAttribute("message","No Transactions");
        }
        model.addAttribute("transactions",account.getTransactionList());
       return "account/accounts";
    }
}
