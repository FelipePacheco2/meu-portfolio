package com.example.maintenanceHospital.repository.order;

import com.example.maintenanceHospital.model.order.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderServiceRepositoy extends JpaRepository<OrderService, Long> {
}
