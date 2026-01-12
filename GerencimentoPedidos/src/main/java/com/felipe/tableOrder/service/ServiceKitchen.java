package com.felipe.tableOrder.service;

import com.felipe.tableOrder.model.ItemOrder;
import com.felipe.tableOrder.repository.RepositoryItemOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ServiceKitchen {

    private Logger logger = Logger.getLogger(ServiceMenuId.class.getName());
    @Autowired
    RepositoryItemOrder repositoryItemOrder;
    @Autowired
    ServiceItemOrder serviceItemOrder;

    public ItemOrder finishOrder(Long id){
        logger.info("Pedido finalizado");
        ItemOrder item = serviceItemOrder.findById(id);
        item.setStatus("Finalizado");
        repositoryItemOrder.save(item);
        return item;
    }


    public List<ItemOrder> findALL() {

        List<ItemOrder> items = repositoryItemOrder.findAll();
        List<ItemOrder> filter = new ArrayList<>();

        for (ItemOrder item : items){
            if(item.getItemMenu().getType().equals("Refeição")){
                filter.add(item);
            }
        }

        return filter;
    }

}
