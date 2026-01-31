package AgroTrackpesagem.demo.mapperDto.surrounded;

import AgroTrackpesagem.demo.mapperDto.MapperGeneric;
import AgroTrackpesagem.demo.mapperDto.animal.AnimalResponseDTO;
import AgroTrackpesagem.demo.model.Animal;
import AgroTrackpesagem.demo.model.Surrounded;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SurroundedMapper extends MapperGeneric<SurroundedDTO, Surrounded> {

    SurroundedResponseDTO toResponseDTOtoDTO(SurroundedDTO surroundedDTO);
    SurroundedResponseDTO toResponseDTO(Surrounded entity);
    List<SurroundedResponseDTO> toResponseDTOList(List<Surrounded> entities);
}
