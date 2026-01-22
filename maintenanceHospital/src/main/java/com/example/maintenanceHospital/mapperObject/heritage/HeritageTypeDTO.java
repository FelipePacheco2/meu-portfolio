package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.model.heritage.CategoryHeritage;
import com.example.maintenanceHospital.model.heritage.CriticallyHeritage;


public record HeritageTypeDTO (
     Long id,
     String name,
     CategoryHeritage category,
     CriticallyHeritage critically,
     String description
){}
