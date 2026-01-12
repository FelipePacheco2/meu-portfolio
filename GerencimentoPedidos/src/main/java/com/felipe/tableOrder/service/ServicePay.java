package com.felipe.tableOrder.service;
import com.felipe.tableOrder.model.ItemOrder;
import com.felipe.tableOrder.model.Order;
import com.felipe.tableOrder.model.PaymentRegister;
import com.felipe.tableOrder.repository.RepositoryPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class ServicePay {

    @Autowired
    RepositoryPay repository;

    public PaymentRegister create(Order order){
        PaymentRegister nf = new PaymentRegister();

        for(ItemOrder item : order.getItems()){

            BigDecimal quantity = transformBigDecimal(item.getQuantity());
            BigDecimal totalValue = quantity.multiply(item.getSale());
            nf.setTotalValue(nf.getTotalValue().add(totalValue));

        }
        nf.setStatus("Aberta");
        nf.addOrder(order);
        return repository.save(nf);
    }

    public BigDecimal transformBigDecimal(int quantity){
        return new BigDecimal(quantity);
    }

    public List<PaymentRegister> findAll(){
        return repository.findAll();
    }


}
