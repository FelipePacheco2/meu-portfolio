package com.felipe.tableOrder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="payRegister")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idPayment")
public class PaymentRegister {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idPayment", nullable = false)
    private Long idPayment;
    @Column(name="totalValue", nullable = false)
    @Positive
    private BigDecimal totalValue = BigDecimal.ZERO;;
    @Column(name="status", nullable = false, columnDefinition = "TEXT")
    private String status;
    @Column(name="date", nullable = false)
    private LocalDateTime date = LocalDateTime.now();
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
        order.setPayment(this);
    }

}
