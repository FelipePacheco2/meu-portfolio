package com.example.maintenanceHospital.mapperObject.heritage;

import com.example.maintenanceHospital.model.heritage.HeritageType;
import com.example.maintenanceHospital.model.heritage.LocationHospital;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record HeritageDTO(
    Long id,
    String tag,
    LocationHospital location,
    HeritageType typeHeritage,
    String serialNumber,
    LocalDateTime dateBuy,
    LocalDateTime warrantyLimit

    // fazer que adicione as horas "2024-12-12T00:00:00"
){
}
