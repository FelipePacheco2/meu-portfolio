package com.felipe.tableOrder.controller;

import com.felipe.tableOrder.model.ItemOrder;
import com.felipe.tableOrder.service.ServiceItemOrder;
import com.felipe.tableOrder.service.ServiceKitchen;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinha")
public class ControllerKitchen {

    @Autowired
    ServiceKitchen serviceKitchen;
    @Autowired
    ServiceItemOrder serviceItemMenu;

    @PutMapping(value = "/finalizado/{id}")
    public ItemOrder finishOrder(@PathVariable Long id){
        return serviceKitchen.finishOrder(id);
    }

    @GetMapping()
    public List<ItemOrder> findAll() {
        return serviceKitchen.findALL();
    }


}
