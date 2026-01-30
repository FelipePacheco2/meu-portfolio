package AgroTrackpesagem.demo.assembler.animal;

import AgroTrackpesagem.demo.assembler.surrounded.SurroundedAssembler;
import AgroTrackpesagem.demo.controller.AnimalController;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnimalAssembler extends RepresentationModelAssemblerSupport<AnimalResponseDTO, EntityModel<AnimalResponseDTO>> {
    private final SurroundedAssembler surroundedAssembler;

    public AnimalAssembler(SurroundedAssembler surroundedAssembler){
        super(AnimalController.class, (Class<EntityModel<AnimalResponseDTO>>) (Class<?>) EntityModel.class);
        this.surroundedAssembler = surroundedAssembler;
    }

    @Override
    public EntityModel<AnimalResponseDTO> toModel(AnimalResponseDTO dto) {


        if(dto.getSurrounded() != null && dto.getSurrounded() instanceof SurroundedResponseDTO){
            SurroundedResponseDTO rawData = (SurroundedResponseDTO) dto.getSurrounded();
            dto.setSurrounded(surroundedAssembler.toModel(rawData));
        }
        return EntityModel.of(dto,
                linkTo(methodOn(AnimalController.class).getById(dto.getId())).withSelfRel(),
                linkTo(methodOn(AnimalController.class).listAll()).withRel("all-animals").withType("GET")
        );
    }

    @Override
    public CollectionModel<EntityModel<AnimalResponseDTO>> toCollectionModel(Iterable<? extends AnimalResponseDTO> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(AnimalController.class).listAll()).withSelfRel().withType("GET"));
    }
}
