package com.felipe.tableOrder.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name="itemOrder")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idItem")
public class ItemOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @ManyToOne
    @JoinColumn(name = "idItemMenu")
    private MenuItemID ItemMenu;
    @Column(name="quantity", nullable = false)
    @Positive
    private int quantity;
    @Column(name="Status", nullable = false, columnDefinition = "TEXT")
    private String status;
    @Column(name="unitValue", nullable = false)
    @Positive
    private BigDecimal sale;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    @JsonIgnore
    private Order order;

}
