package com.felipe.tableOrder.controller;


import com.felipe.tableOrder.DTO.OrderDTO;
import com.felipe.tableOrder.model.Order;
import com.felipe.tableOrder.service.ServiceOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garcao")
public class ControllerOrder {

    @Autowired
    private ServiceOrder serviceOrder;

    @PostMapping("/criarPedido")
    public Order createOrder(@RequestBody OrderDTO dto){return serviceOrder.create(dto);}

    @GetMapping()
    public List<Order> findAlL(){return serviceOrder.findAll();}

}