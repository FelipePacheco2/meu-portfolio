package com.example.maintenanceHospital.repository.order;

import com.example.maintenanceHospital.model.order.Occurrence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {
}
