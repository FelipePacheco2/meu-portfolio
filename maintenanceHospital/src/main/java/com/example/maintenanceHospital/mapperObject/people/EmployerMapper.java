package com.example.maintenanceHospital.mapperObject.people;

import com.example.maintenanceHospital.mapperObject.order.MapperGeneric;
import com.example.maintenanceHospital.model.people.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployerMapper extends MapperGeneric<EmployeeDTO, Employee> {
}
