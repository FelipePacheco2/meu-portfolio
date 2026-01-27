package com.example.maintenanceHospital.controller.order;

import com.example.maintenanceHospital.assembler.order.OrderServiceAssembler;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import com.example.maintenanceHospital.servico.order.OrderServiceService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/os")
public class OrderServiceController {


    private final OrderServiceService service;
    private final OrderServiceAssembler orderServiceAssembler;

    public OrderServiceController(OrderServiceService service, OrderServiceAssembler assembler) {
        this.service = service;
        this.orderServiceAssembler = assembler;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<OrderServiceDTO>>> findAll(){
        return ResponseEntity.ok(orderServiceAssembler.toCollectionModel(service.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderServiceDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(orderServiceAssembler.toModel(service.findById(id)));
    }

    @PostMapping()
    public ResponseEntity<EntityModel<OrderServiceDTO>> create(@RequestBody OrderServiceDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderServiceAssembler.toModel(service.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OrderServiceDTO>> update(@PathVariable Long id, @RequestBody OrderServiceDTO dto) {
        return ResponseEntity.ok(orderServiceAssembler.toModel(service.update(id, dto)));
    }
}
