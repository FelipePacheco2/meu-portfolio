package com.example.maintenanceHospital.mapperObject.order;

import com.example.maintenanceHospital.mapperObject.MapperGeneric;
import com.example.maintenanceHospital.model.order.Occurrence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OccurrenceMapper extends MapperGeneric<OccurrenceDTO, Occurrence> {
}
