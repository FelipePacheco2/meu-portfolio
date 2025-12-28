package com.br.felipe.Controller;

import java.util.List;
import java.util.Scanner;

import com.br.felipe.Model.Customer;
import com.br.felipe.Service.CustomerService;
import com.br.felipe.Views.AllViews;


public class CustomerController {
    private AllViews view; 
    private CustomerService service;
    private Scanner input;

    public CustomerController() {
        this.service = new CustomerService();
        this.input = new Scanner(System.in);
        // NÃO instancie "new AllViews()" aqui se AllViews instancia new CustomerController() da merda"
    }
    

    public void AdmMenu(){
        view = new AllViews();
        int option = -1;
        
        while(option != 0){
            view.DisplayADMMenu();
            option = input.nextInt();
            switch (option) {
                case 1 -> Create();
                case 2 -> ReadALL();
                case 3 -> ReadOne();
                case 4 -> Update();
                case 5 -> Delete();
                case 0 -> view.iniciaSistema();
                default -> view.ErroOptionFound();
            }
        }
    }

   public void Create(){
        view = new AllViews();
        Customer customer = view.CreateBuilderCustumer();
        Boolean success = service.save(customer);

        if(success){
            view.TableCustomerNotId(customer);
            view.SuccessCustomerRegistered();
        } else {
            view.ErroCustomerNotRegistered();
        }
        AdmMenu();
   }
    
    public void ReadALL(){
        view = new AllViews();
        List<Customer> customers = service.ListAll();

        if (customers != null && !customers.isEmpty()) {
            view.displayAllCustomers(customers);
        } else {
            view.ErrEmptyList();
        }

        AdmMenu();
    }

    public Customer ReadOne(){
        view = new AllViews();
        int id = view.CaptureCustomerID();
        Customer customer = service.PrintOne(id);
        
        if(customer != null){
            view.TableCustomer(customer);
            return customer;
        }else{
            view.ErroCustomerNotFound();
            return null;
        } 
    }

    public void Update(){ 
        view = new AllViews();
        Customer customer = ReadOne();
        if(view.check() == 1){
            customer = view.CreateBuilderCustumerid(customer.getIdCustomer());
            boolean success = service.Update(customer);

            if(success){
                view.SuccessCustomerUpdate();
                view.TableCustomer(customer);
                view.SuccessCustomerUpdate();
            }else{
                view.ErroCustomerNotUpdate();
            }
        }
        AdmMenu();
    }

    public void Delete(){
        view = new AllViews();
        Customer customer = ReadOne();
        if(customer != null && view.check() == 1 ){
            boolean success = service.Delete(customer.getIdCustomer());
            if(success){
                view.SuccessCustomerDelete();
            }else{
                view.ErroCustomerNotUpdate();
            }
        }

        AdmMenu();
    }
}
