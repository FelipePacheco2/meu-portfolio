package com.example.maintenanceHospital.repository.heritage;

import com.example.maintenanceHospital.model.heritage.HeritageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeritageTypeRepository extends JpaRepository<HeritageType, Long> {
    @Query("SELECT DISTINCT o FROM HeritageType o LEFT JOIN FETCH o.category")
    List<HeritageType> findAllFull();

    @Query("SELECT o FROM HeritageType o LEFT JOIN FETCH o.category WHERE o.id = :id")
    HeritageType findByIdObject(@Param("id") Long id);

}
