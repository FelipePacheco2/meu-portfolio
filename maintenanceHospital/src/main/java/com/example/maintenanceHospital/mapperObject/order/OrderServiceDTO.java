package com.example.maintenanceHospital.mapperObject.order;

import com.example.maintenanceHospital.model.heritage.CriticallyHeritage;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.people.Employee;
import java.util.List;

public record OrderServiceDTO(
     Long id,
     String protocol,
     Employee technical,
     List<Occurrence> occurrences,
     CriticallyHeritage priority,
     StatusOccurrence statusService,
     String description
    ){}
