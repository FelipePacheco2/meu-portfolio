package com.felipe.tableOrder.repository;

import com.felipe.tableOrder.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryOrder extends JpaRepository<Order, Long> {
}
