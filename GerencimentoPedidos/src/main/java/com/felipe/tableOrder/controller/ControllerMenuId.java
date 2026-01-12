package com.felipe.tableOrder.controller;


import com.felipe.tableOrder.model.MenuItemID;
import com.felipe.tableOrder.service.ServiceMenuId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Menu")
public class ControllerMenuId {

    @Autowired
    ServiceMenuId service;

    @GetMapping()
    public List<MenuItemID> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuItemID findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping(value = "/all", consumes = "application/json")
    public List<MenuItemID> createAll(@RequestBody List<MenuItemID> listItem){return service.createAll(listItem);}

    @PostMapping()
    public MenuItemID create(@RequestBody MenuItemID item){return service.create(item);}

    @PutMapping()
    public MenuItemID update(@RequestBody MenuItemID item){return service.update(item);}

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
