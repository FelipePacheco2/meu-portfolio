package com.example.maintenanceHospital.repository.heritage;

import com.example.maintenanceHospital.model.heritage.Heritage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeritageRepository extends JpaRepository<Heritage, Long> {
    Optional<Heritage> findTopByOrderByIdDesc();
}
