package com.example.maintenanceHospital.assembler.order;

import com.example.maintenanceHospital.controller.order.OccurrenceController;
import com.example.maintenanceHospital.mapperObject.order.OccurrenceDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OccurrenceAssembler extends RepresentationModelAssemblerSupport<OccurrenceDTO, EntityModel<OccurrenceDTO>> {

    public OccurrenceAssembler() {
        // pega os urls
        super(OccurrenceController.class, (Class<EntityModel<OccurrenceDTO>>) (Class<?>) EntityModel.class);
    }

    @Override
    public EntityModel<OccurrenceDTO> toModel(OccurrenceDTO dto) {
        // adicionando link para cada dto
        return EntityModel.of(dto,
                linkTo(methodOn(OccurrenceController.class).findAll()).withRel("allOrders").withType("GET"),
                linkTo(methodOn(OccurrenceController.class).findById(dto.id())).withSelfRel().withType("GET"),
                linkTo(methodOn(OccurrenceController.class).create(dto)).withRel("create").withType("POST"),
                linkTo(methodOn(OccurrenceController.class).update(dto.id(), null)).withRel("update").withType("PUT")
        );
    }

    @Override
    public CollectionModel<EntityModel<OccurrenceDTO>> toCollectionModel(Iterable<? extends OccurrenceDTO> entities) {
        // adiciona um link para a própria lista
        return super.toCollectionModel(entities)
                .add(
                    linkTo(methodOn(OccurrenceController.class).findAll()).withSelfRel().withType("GET")
                );
    }
}