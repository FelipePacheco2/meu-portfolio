package com.example.maintenanceHospital.repository.heritage;

import com.example.maintenanceHospital.model.heritage.Heritage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeritageRepository extends JpaRepository<Heritage, Long> {
    @Query("SELECT DISTINCT h FROM Heritage h LEFT JOIN h.typeHeritage LEFT JOIN FETCH h.location")
    List<Heritage> findAllFull();

    @Query("SELECT h FROM Heritage h LEFT JOIN FETCH h.typeHeritage LEFT JOIN FETCH h.location WHERE h.id = :id")
    Heritage findByIdObjet(@Param("id") Long id);
}