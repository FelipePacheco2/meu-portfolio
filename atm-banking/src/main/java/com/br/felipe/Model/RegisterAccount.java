package com.br.felipe.Model;

import java.math.BigDecimal;
import java.sql.Date;

public class RegisterAccount {
    private int register_id;
    private String reason;
    private BigDecimal value;
    private int account_id;
    private Date date;

    public int getRegister_id() {
        return register_id;
    }
    public void setRegister_id(int register_id) {
        this.register_id = register_id;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public int getAccount_id() {
        return account_id;
    }
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public BigDecimal getValue() {
        return value;
    }
    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public RegisterAccount(){
    }

    public RegisterAccount(String reason, BigDecimal value, int account_id){
        this.reason = reason;
        this.value = value;
        this.account_id = account_id;
    }

}
