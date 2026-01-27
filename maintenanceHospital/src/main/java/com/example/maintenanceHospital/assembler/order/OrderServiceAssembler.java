package com.example.maintenanceHospital.assembler.order;

import com.example.maintenanceHospital.controller.order.OrderServiceController;
import com.example.maintenanceHospital.mapperObject.order.OrderServiceDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderServiceAssembler extends RepresentationModelAssemblerSupport<OrderServiceDTO, EntityModel<OrderServiceDTO>> {

    public OrderServiceAssembler(OccurrenceAssembler occurrenceAssembler) {
        // pega os urls
        super(OrderServiceController.class, (Class<EntityModel<OrderServiceDTO>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<OrderServiceDTO> toModel(OrderServiceDTO dto) {
        // adicionando link para cada dto

        return EntityModel.of(dto,
                linkTo(methodOn(OrderServiceController.class).findAll()).withRel("allOrders").withType("GET"),
                linkTo(methodOn(OrderServiceController.class).findById(dto.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(OrderServiceController.class).create(dto)).withRel("create").withType("POST"),
                linkTo(methodOn(OrderServiceController.class).update(dto.id(), null)).withRel("update").withType("PUT")

        );
    }

    @Override
    public CollectionModel<EntityModel<OrderServiceDTO>> toCollectionModel(Iterable<? extends OrderServiceDTO> entities) {
        // adiciona um link para a própria lista
        return super.toCollectionModel(entities)
                .add(
                    linkTo(methodOn(OrderServiceController.class).findAll()).withSelfRel().withType("GET")
                );
    }
}