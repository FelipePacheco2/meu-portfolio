package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.mapperObject.MapperGeneric;
import com.example.maintenanceHospital.model.heritage.HeritageType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeritageTypeMapper extends MapperGeneric<HeritageTypeDTO, HeritageType> {
}
