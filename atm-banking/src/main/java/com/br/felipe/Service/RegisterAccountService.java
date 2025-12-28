package com.br.felipe.Service;

import com.br.felipe.Model.RegisterAccount;
import com.br.felipe.Repository.RegisterAccountRepository;

public class RegisterAccountService {
    private RegisterAccountRepository repository;

    public RegisterAccountService(){
        this.repository = new RegisterAccountRepository();
    }


    public void save(RegisterAccount register){
        repository.create(register);
    }

    
}
