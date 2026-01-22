package com.example.maintenanceHospital.repository.order;

import com.example.maintenanceHospital.model.order.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderServiceRepositoy extends JpaRepository<OrderService, Long> {

    @Query("SELECT DISTINCT o FROM OrderService o LEFT JOIN FETCH o.technical LEFT JOIN FETCH o.occurrences")
    List<OrderService> findAllCompleto();

}
