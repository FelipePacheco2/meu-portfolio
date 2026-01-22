package com.example.maintenanceHospital.repository.people;

import com.example.maintenanceHospital.model.people.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
