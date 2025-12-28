package com.br.felipe.Model;

public class Customer {
    private Integer idCustomer;
    private String firstName;
    private String lastName;
    private String cpf;

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public Customer(){}

    public Customer(String firstName, String lastName, String cpf){
        this.idCustomer = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

     public Customer(Integer idCustomer, String firstName, String lastName, String cpf){
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

  
    
}
