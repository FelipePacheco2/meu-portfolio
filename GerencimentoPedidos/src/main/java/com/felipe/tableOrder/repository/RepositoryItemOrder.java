package com.felipe.tableOrder.repository;

import com.felipe.tableOrder.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryItemOrder extends JpaRepository<ItemOrder, Long> {
}
