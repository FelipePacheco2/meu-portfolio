package com.example.maintenanceHospital.mapperObject.order;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

public interface MapperGeneric<D, E> {
    D toDTO(E entity);
    E toEntity(D dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(D dto, @MappingTarget E entity);
    List<D> toDTOList(List<E> entity);
}
