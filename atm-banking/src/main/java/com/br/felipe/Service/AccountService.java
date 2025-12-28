package com.br.felipe.Service;

import java.math.BigDecimal;

import com.br.felipe.Model.Account;
import com.br.felipe.Model.RegisterAccount;
import com.br.felipe.Repository.AccountRepository;


public class AccountService {
    private AccountRepository repository;
    private RegisterAccountService registerAccount;
    private static final String AMOUNT_WITHDRAWN = "Sacado";
    private static final String AMOUNT_DEPOSITED = "Depositado";

    public AccountService(){
        this.repository = new AccountRepository();
        this.registerAccount = new RegisterAccountService();
    }

    public void Save(int customer_id){
        BigDecimal balance = BigDecimal.ZERO;

        Account account = new Account(balance, customer_id);
        repository.Create(account);
    }

    public boolean DepositUpdate(Account account, BigDecimal value){

        if(value == null || value.compareTo(BigDecimal.ZERO) <= 0){
            return false;
        }

        BigDecimal newValue = account.getBalance().add(value);

        try {
            boolean sucess = repository.Update(account.getCustomer_id(), newValue);
                if(sucess){
                    registerAccount.save(new RegisterAccount(AMOUNT_DEPOSITED, value, account.getId()));
                    account.setBalance(newValue);
                    return true;
                }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return false;
    }

    public boolean Withdraw(Account account, BigDecimal value){

        if(value == null || value.compareTo(BigDecimal.ZERO) <= 0 || account.getBalance().compareTo(value) < 0){
            return false;
        }

        BigDecimal newValue = account.getBalance().subtract(value);

        try {
            boolean success = repository.Update(account.getCustomer_id(), newValue);
            if(success){
                registerAccount.save(new RegisterAccount(AMOUNT_WITHDRAWN, value, account.getId()));
                account.setBalance(newValue);
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

         return false;
    }

    public Account PrintOne(int id){
        try {
            return repository.ReadOne(id);
        } catch (Exception e) {
            return null;
        }
    }
}
