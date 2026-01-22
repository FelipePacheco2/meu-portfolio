package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.mapperObject.order.MapperGeneric;
import com.example.maintenanceHospital.model.heritage.Heritage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HeritageMapper extends MapperGeneric<HeritageDTO, Heritage> {
}
