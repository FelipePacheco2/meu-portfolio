package AgroTrackpesagem.demo.assembler.surrounded;

import AgroTrackpesagem.demo.controller.AnimalController;
import AgroTrackpesagem.demo.controller.SurroundedController;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SurroundedAssembler extends RepresentationModelAssemblerSupport<SurroundedResponseDTO, EntityModel<SurroundedResponseDTO>> {

        public SurroundedAssembler() {
        super(SurroundedMapper.class, (Class<EntityModel<SurroundedResponseDTO>>) (Class<?>) EntityModel.class);
    }

        @Override
        public EntityModel<SurroundedResponseDTO> toModel (SurroundedResponseDTO dto){
        // O linkTo(methodOn(...)) deve referenciar o AnimalController

        return EntityModel.of(dto,
                linkTo(methodOn(AnimalController.class).getById(dto.getId())).withSelfRel(),
                linkTo(methodOn(AnimalController.class).listAll()).withRel("all-animals").withType("GET")
        );
    }

        @Override
        public CollectionModel<EntityModel<SurroundedResponseDTO>> toCollectionModel (Iterable < ? extends
        SurroundedResponseDTO > entities){
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(AnimalController.class).listAll()).withSelfRel().withType("GET"));
    }
}
