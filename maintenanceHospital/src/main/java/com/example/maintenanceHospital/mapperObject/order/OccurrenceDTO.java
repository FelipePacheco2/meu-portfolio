package com.example.maintenanceHospital.mapperObject.order;


import com.example.maintenanceHospital.model.heritage.CriticallyHeritage;
import com.example.maintenanceHospital.model.heritage.Heritage;
import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import com.example.maintenanceHospital.model.people.Employee;

import java.time.LocalDateTime;

public record OccurrenceDTO(
    Long id,
    String protocol,
    Heritage heritage,
    String description,
    Employee applicant,
    String sector,
    StatusOccurrence status,
    OrderService orderService,
    LocalDateTime date
) {
}
