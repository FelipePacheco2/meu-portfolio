package com.felipe.tableOrder.service;

import com.felipe.tableOrder.model.MenuItemID;
import com.felipe.tableOrder.repository.RepositoryMenuId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ServiceMenuId {

    private Logger logger = Logger.getLogger(ServiceMenuId.class.getName());

   @Autowired
   RepositoryMenuId repository;

   public MenuItemID create(MenuItemID item){
       logger.info("Create item");

       return repository.save(item);
   }

   public List<MenuItemID> createAll(List<MenuItemID> listItem){
       logger.info("Create All item");
       return repository.saveAll(listItem);
   }

   public List<MenuItemID> findAll(){
       logger.info("Find ALl item");

       return repository.findAll();
   }

   public MenuItemID findById(Long id){
       logger.info("Scanner id");
       return repository.findById(id)
               .orElseThrow();
   }

   public MenuItemID update(MenuItemID item){
       logger.info("Update");

       return repository.save(item);
   }

   public void delete(Long id){
       logger.info("delete");
       MenuItemID entity = repository.findById(id)
               .orElseThrow();
       repository.delete(entity);
   }

}
