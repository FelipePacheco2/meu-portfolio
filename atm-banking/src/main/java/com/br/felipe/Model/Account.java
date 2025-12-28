package com.br.felipe.Model;

import java.math.BigDecimal;

public class Account {
    int id;
    BigDecimal balance;
    int customer_id;

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public int getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void sacar(){
        
    }

    public void depositar(){

    }


    public Account(){}
    public Account(BigDecimal balance, int customer_id){
        this.balance = balance;
        this.customer_id = customer_id;
    }
    public Account(int id, BigDecimal balance, int customer_id){
        this.id = id;
        this.balance = balance;
        this.customer_id = customer_id;
    }
}
