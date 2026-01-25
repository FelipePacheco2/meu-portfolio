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
    List<Occurrence> findAllFull();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Occurrence o SET o.status = :status, o.orderService = :order " +
            "WHERE o.id IN :ids " +
            "AND o.status = :statusPendente " +
            "AND o.orderService IS NULL")
    int updateStatusAndLinkOrder(
            @Param("ids") List<Long> ids,
            @Param("order") OrderService order,
            @Param("status") StatusOccurrence status,
            @Param("statusPendente") StatusOccurrence statusPendente
    );


    @Query("SELECT o FROM Occurrence o LEFT JOIN FETCH o.orderService WHERE o.id in :id")
    Occurrence findByIdObject(@Param("id") Long id);

    @Query("SELECT COUNT(o) FROM Occurrence o WHERE o.id IN :ids AND o.status = 'PENDENTE' AND o.orderService IS NULL")
    Long countPendingAndAvailable(@Param("ids") List<Long> ids);

    @Query("SELECT DISTINCT o FROM Occurrence o WHERE o.id in :ids AND o.orderService = :order")
    List<Occurrence> occurrenceSucess(
            @Param("ids") List<Long> ids,
            @Param("order") OrderService order
    );

    @Query("SELECT o FROM Occurrence o WHERE o.id IN :ids AND (o.orderService IS NULL OR o.orderService != :order)")
    List<Occurrence> findFailedOccurrences(
            @Param("ids") List<Long> ids,
            @Param("order") OrderService order
    );
}