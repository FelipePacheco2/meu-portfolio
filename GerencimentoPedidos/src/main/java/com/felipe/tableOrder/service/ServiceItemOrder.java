package com.felipe.tableOrder.service;

import com.felipe.tableOrder.DTO.ItemOrderDTO;
import com.felipe.tableOrder.model.ItemOrder;
import com.felipe.tableOrder.model.MenuItemID;
import com.felipe.tableOrder.repository.RepositoryItemOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ServiceItemOrder {

    private Logger logger = Logger.getLogger(ServiceMenuId.class.getName());
    @Autowired
    RepositoryItemOrder repository;
    @Autowired
    ServiceMenuId serviceMenuId;

    public ItemOrder findById(Long id){
        logger.info("item buscado");
        return repository.findById(id)
                .orElseThrow();
    }

    public ItemOrder create(ItemOrderDTO dto){
        MenuItemID itemMenu = serviceMenuId.findById(dto.getIdMenu());
        ItemOrder item = new ItemOrder();

        item.setItemMenu(itemMenu);
        item.setQuantity(dto.getQuantity());

        if(itemMenu.getType().equals("Refeição")){
            item.setStatus("Preparando");
        }else{
            item.setStatus("pronto");
        }

        item.setSale(itemMenu.getSale());

        return item;
    }

}
