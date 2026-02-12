package AgroTrackpesagem.demo.assembler;

import AgroTrackpesagem.demo.controller.AnimalController;
import AgroTrackpesagem.demo.controller.SurroundedController;
import AgroTrackpesagem.demo.controller.WeighingController;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedMapper;
import AgroTrackpesagem.demo.mapperDto.surrounded.SurroundedResponseDTO;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingMapper;
import AgroTrackpesagem.demo.mapperDto.weighing.WeighingResponseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class WeighingAssembler extends
        RepresentationModelAssemblerSupport<WeighingResponseDTO, EntityModel<WeighingResponseDTO>> {

        public WeighingAssembler() {
        super(WeighingMapper.class, (Class<EntityModel<WeighingResponseDTO>>) (Class<?>) EntityModel.class);
    }

        @Override
        public EntityModel<WeighingResponseDTO> toModel (WeighingResponseDTO dto){
            WeighingController controller = methodOn(WeighingController.class);
        return EntityModel.of(dto,
                linkTo(controller.listAll(dto.getAnimal().tagIdentifier())).withRel("find-All-Tag-weighing").withType("GET"),
                linkTo(controller.create(null)).withRel("create-weighing").withType("POST")
        );

    }

    @Override
    public CollectionModel<EntityModel<WeighingResponseDTO>> toCollectionModel (Iterable < ? extends
            WeighingResponseDTO > entities){
        return super.toCollectionModel(entities)
                .add(linkTo(methodOn(AnimalController.class).listAll()).withSelfRel().withType("GET"));
    }

}
