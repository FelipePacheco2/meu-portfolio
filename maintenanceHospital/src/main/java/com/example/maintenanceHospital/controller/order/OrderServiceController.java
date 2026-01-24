package com.example.maintenanceHospital.controller.order;

import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.servico.order.OrderServiceService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
public class OrderServiceController {

    @Autowired
    OrderServiceService service;

    @Transactional
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<OrderServiceDTO> findAll(){
        return service.findAll();
    }

    @Transactional
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderServiceDTO create(@RequestBody OrderServiceDTO dto){
        return service.create(dto);
    }
}
