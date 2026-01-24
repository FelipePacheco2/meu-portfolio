package com.example.maintenanceHospital.repository.order;

import com.example.maintenanceHospital.model.heritage.StatusOccurrence;
import com.example.maintenanceHospital.model.order.Occurrence;
import com.example.maintenanceHospital.model.order.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OccurrenceRepository extends JpaRepository<Occurrence, Long> {

    @Query("SELECT DISTINCT o FROM Occurrence o LEFT JOIN o.orderService")
    List<Occurrence> findAllCompleto();

    @Modifying
    @Query("UPDATE Occurrence o SET o.status = :status, o.orderService = :order WHERE o.id IN :ids")
    void updateStatusAndLinkOrder(
            @Param("ids") List<Long> ids,
            @Param("order") OrderService order,
            @Param("status") StatusOccurrence status
    );
}