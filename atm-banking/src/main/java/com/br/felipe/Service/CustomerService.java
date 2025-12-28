package com.br.felipe.Service;

import java.util.List;

import com.br.felipe.Model.Customer;
import com.br.felipe.Repository.CustomerRepository;

import br.com.caelum.stella.validation.CPFValidator;

public class CustomerService {
    
    private CustomerRepository repository;
    private AccountService accountService;

    public CustomerService(){
        this.repository = new CustomerRepository();
        this.accountService = new AccountService();
    }

    public Boolean save(Customer customer){
        
        try {
            if(IsCpfValid(customer.getCpf())){ 
                int id = repository.Create(customer);
                if(id > 0){
                    accountService.Save(id);
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return false;
    }

    public List<Customer> ListAll(){
        return repository.ReadAll();
    }

    public Customer PrintOne(int id){
        return repository.ReadOne(id);
    }

    public boolean Update(Customer customer){
        try {
            repository.Update(customer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean Delete(int id){
        try {
            repository.Delete(id);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    } 

    public boolean IsCpfValid(String cpf){
        CPFValidator validation = new CPFValidator();

        try {
            validation.assertValid(cpf);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
