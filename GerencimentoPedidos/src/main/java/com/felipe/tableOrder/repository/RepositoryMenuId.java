package com.felipe.tableOrder.repository;

import com.felipe.tableOrder.model.MenuItemID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryMenuId extends JpaRepository<MenuItemID, Long> {
}
