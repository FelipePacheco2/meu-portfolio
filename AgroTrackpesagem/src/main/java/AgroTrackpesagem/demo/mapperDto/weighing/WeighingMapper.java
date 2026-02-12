package AgroTrackpesagem.demo.mapperDto.weighing;

import AgroTrackpesagem.demo.mapperDto.MapperGeneric;
import AgroTrackpesagem.demo.model.Weighing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WeighingMapper extends MapperGeneric<WeighingDTO, Weighing> {

    // ignore o campo surrounded porque eu vou cuidar dele no Service
    @Mapping(source = "animal", target = "animal")
    WeighingResponseDTO toResponse(Weighing entity);
    List<WeighingResponseDTO> toListResponse(List<Weighing> list);
}
