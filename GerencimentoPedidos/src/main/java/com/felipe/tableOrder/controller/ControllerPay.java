package com.felipe.tableOrder.controller;

import com.felipe.tableOrder.model.PaymentRegister;
import com.felipe.tableOrder.service.ServicePay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class ControllerPay {

    @Autowired
    ServicePay servicePay;

    @GetMapping
    public List<PaymentRegister> findAll(){return servicePay.findAll();}
}
