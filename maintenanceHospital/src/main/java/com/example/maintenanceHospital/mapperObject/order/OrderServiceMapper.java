package com.example.maintenanceHospital.mapperObject.order;

import com.example.maintenanceHospital.mapperObject.MapperGeneric;
import com.example.maintenanceHospital.model.order.OrderService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderServiceMapper extends MapperGeneric<OrderServiceDTO, OrderService> {
}
