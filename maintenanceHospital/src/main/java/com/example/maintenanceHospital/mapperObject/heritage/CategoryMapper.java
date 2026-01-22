package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.mapperObject.order.MapperGeneric;
import com.example.maintenanceHospital.model.heritage.CategoryHeritage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends MapperGeneric<CategoryHeritageDTO, CategoryHeritage> {
}
