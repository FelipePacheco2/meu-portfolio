package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.mapperObject.order.MapperGeneric;
import com.example.maintenanceHospital.model.heritage.LocationHospital;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationHospitalMapper extends MapperGeneric<LocationHospitalDTO, LocationHospital> {
}
