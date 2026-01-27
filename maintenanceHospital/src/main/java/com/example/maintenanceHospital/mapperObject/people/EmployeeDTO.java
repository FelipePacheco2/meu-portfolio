package com.example.maintenanceHospital.mapperObject.people;

import com.example.maintenanceHospital.model.people.RoleEmployee;

public record EmployeeDTO(
    Long id,
    String name,
    RoleEmployee role
){
}
