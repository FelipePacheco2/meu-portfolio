package com.felipe.tableOrder.repository;

import com.felipe.tableOrder.model.PaymentRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPay extends JpaRepository<PaymentRegister, Long> {
}
