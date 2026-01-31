package AgroTrackpesagem.demo.assembler;

import AgroTrackpesagem.demo.assembler.surrounded.SurroundedAssembler;
import AgroTrackpesagem.demo.controller.AnimalController;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalSurroundedMoveDTO;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AnimalAssembler extends RepresentationModelAssemblerSupport<AnimalResponseDTO, EntityModel<AnimalResponseDTO>> {
    private final SurroundedAssembler surroundedAssembler;
    private final SurroundedMapper mapper;

    public AnimalAssembler(SurroundedAssembler surroundedAssembler, SurroundedMapper mapper){
        super(AnimalController.class, (Class<EntityModel<AnimalResponseDTO>>) (Class<?>) EntityModel.class);
        this.surroundedAssembler = surroundedAssembler;
        this.mapper = mapper;
    }

    @Override
    public EntityModel<AnimalResponseDTO> toModel(AnimalResponseDTO dto) {
        AnimalController controller = methodOn(AnimalController.class);

        EntityModel<AnimalResponseDTO> animalModel = EntityModel.of(dto,
                linkTo(controller.getById(dto.getId())).withSelfRel().withType("GET"),
                linkTo(controller.listAll()).withRel("list-all").withType("GET"),
                linkTo(controller.create(null)).withRel("create-animal").withType("POST"),
                linkTo(controller.update(dto.getId(), null)).withRel("Update-animal").withType("PUT"),
                linkTo(controller.moveAnimal(dto.getId(), new AnimalSurroundedMoveDTO(null))).withRel("move-animal").withType("PUT"),
                linkTo(controller.updateStatus(dto.getId(), null)).withRel("update-status").withType("PUT")
        );
        if (dto.getSurrounded() != null) {

            // A gente usa o surroundedAssembler para criar os links daquele objeto
            EntityModel<SurroundedResponseDTO> surroundedModel =
                    surroundedAssembler.toModel(mapper.toResponseDTOtoDTO(dto.getSurrounded()));

            animalModel.add(surroundedModel.getRequiredLink(IanaLinkRelations.SELF)
                    .withRel("surrounded-details"));

        }

        return animalModel;
    }
}
