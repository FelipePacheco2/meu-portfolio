package com.felipe.tableOrder.service;

import com.felipe.tableOrder.DTO.ItemOrderDTO;
import com.felipe.tableOrder.DTO.OrderDTO;
import com.felipe.tableOrder.model.ItemOrder;
import com.felipe.tableOrder.model.MenuItemID;
import com.felipe.tableOrder.model.Order;
import com.felipe.tableOrder.model.PaymentRegister;
import com.felipe.tableOrder.repository.RepositoryMenuId;
import com.felipe.tableOrder.repository.RepositoryOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ServiceOrder {

    private Logger logger = Logger.getLogger(ServiceMenuId.class.getName());
    @Autowired
    RepositoryOrder repository;
    @Autowired
    ServiceItemOrder serviceItemOrder;
    @Autowired
    ServicePay servicePay;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        return  repository.findById(id)
                .orElseThrow();
    }

    public Order create(OrderDTO dto){
        Order order = new Order();
        order.setNumberTable(dto.getNtable());

        for(ItemOrderDTO itemDTO : dto.getItems()){
            ItemOrder item = serviceItemOrder.create(itemDTO);
            order.addItems(item);
        }

        PaymentRegister nf = servicePay.create(order);

        return order;
    }

}
