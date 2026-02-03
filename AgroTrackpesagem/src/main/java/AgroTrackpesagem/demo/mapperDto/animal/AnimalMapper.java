package AgroTrackpesagem.demo.mapperDto.animal;

import AgroTrackpesagem.demo.mapperDto.MapperGeneric;
import AgroTrackpesagem.demo.model.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public  interface AnimalMapper extends MapperGeneric<AnimalDTO, Animal>{
    @Override// ignore o campo surrounded porque eu vou cuidar dele no Service
    @Mapping(target = "surrounded", ignore = true)
    Animal toEntity(AnimalDTO dto);
    @Override
    @Mapping(target = "surrounded", source = "surrounded.id") // pegue apenas o ID do objeto surrounded e coloque no Long do DTO"
    AnimalDTO toDTO(Animal entity);
    @Override
    @Mapping(target = "surrounded", ignore = true)
    void updateEntityFromDTO(AnimalDTO dto, @MappingTarget Animal entity);

    void updateEntityFromDTO(AnimalUpdateDTO dto, @MappingTarget Animal entity);

    AnimalDTO toDtoMove(AnimalSurroundedMoveDTO dto);
    AnimalResponseDTO toResponseDTO(Animal entity);
    List<AnimalResponseDTO> toResponseDTOList(List<Animal> entities);
}

