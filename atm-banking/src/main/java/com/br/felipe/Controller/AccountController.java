package com.br.felipe.Controller;

import java.math.BigDecimal;
import java.util.Scanner;

import com.br.felipe.Model.Account;
import com.br.felipe.Service.AccountService;
import com.br.felipe.Views.AllViews;

public class AccountController {
    private AccountService service;
    private Scanner input = new Scanner(System.in);
    private AllViews view; 


    public AccountController() {
        this.service = new AccountService();
        // NÃO coloque this.view = new AllViews() aqui dar merda;
    }

    public void CustomerMenu(){
        view = new AllViews();
        int opcao = -1;
        while(opcao != 0){
            view.DisplayCustomerMenu();
             
            opcao = input.nextInt();
            switch (opcao) {
                //case 1 -> ver Perfil;
                case 2 -> Withdraw();
                case 3 -> Deposit();
                case 0 -> view.iniciaSistema();
                default -> view.ErroOptionFound();
            }
        }
    }

    public void Deposit(){
        Account account = ReadOne();
        if(account != null){ 
            BigDecimal value = view.Movement();
            boolean sucess = service.DepositUpdate(account, value);
            if(sucess){
                view.SuccessDeposit(value);
            }else{
                view.ErrDeposit();
            }
        }
        
        CustomerMenu();
    }

    public void Withdraw(){
        view = new AllViews();
        Account account = ReadOne();
        if(account != null){ 
            BigDecimal value = view.Movement();
            boolean sucess = service.Withdraw(account, value);

            if(sucess){
                view.SucessDraw(value);
            }else{
                view.ErrDraw();
            }
        }
        CustomerMenu();
    }

    public Account ReadOne(){
        view = new AllViews();
        int id = view.CaptureCustomerID();

        Account account = service.PrintOne(id);
        if(account != null){
            return account;
        }else{
            view.ErroCustomerNotFound();
            return null;
        }
    }
}
